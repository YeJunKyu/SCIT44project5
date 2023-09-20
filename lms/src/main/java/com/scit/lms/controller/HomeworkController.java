package com.scit.lms.controller;

import com.scit.lms.domain.*;
import com.scit.lms.service.HomeworkService;
import com.scit.lms.util.FileService;
import com.scit.lms.util.PageNavigator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("homework")
public class HomeworkController {

    @Autowired
    HomeworkService service;

    //과제 파일 저장경로
    @Value("${homework.servlet.multipart.location}")
    String uploadPath;

    //과제 파일 저장경로
    @Value("${homeworksub.servlet.multipart.location}")
    String uploadsubPath;

    //게시판 목록의 페이지당 글 수
    @Value("${user.board.page}")
    int countPerPage;

    //게시판 목록의 페이지 이동 링크 수
    @Value("${user.board.group}")
    int pagePerGroup;

    //과제 메인 페이지
    @GetMapping({"","/"})
    public String homework(Model model
            , String type
            , String searchWord
            , @AuthenticationPrincipal UserDetails user
            , @RequestParam (name="page", defaultValue="1") int page) {

        //카테고리
        ArrayList<HomeworkCategory> category = service.homeworkCategory();

        //커리큘럼 중복 제거
        List<String> curriculum = category.stream()
                .map(HomeworkCategory::getCurriculum)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("category", category);
        model.addAttribute("curriculum", curriculum);

        //페이지네이션
        PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);

        //전체 과제 글 리스트
        ArrayList<Homework> list = service.hwList(navi, type, searchWord);

        //학생 과제 글 리스트
        if (user.getAuthorities().stream().anyMatch(auth -> "ROLE_student".equals(auth.getAuthority()))) {

            //학생 커리큘럼, 과목, 반
            HomeworkCategory studentCategory = service.studentCategory(user.getUsername());

            //과제 리스트
            list = service.studentHwList(studentCategory, navi, type, searchWord);

            //페이지네이션
            navi = service.getPageNavigatorStudent(pagePerGroup, countPerPage, page, type, searchWord, studentCategory);

        }

        model.addAttribute("list", list);
        model.addAttribute("navi", navi);
        model.addAttribute("type", type);
        model.addAttribute("searchWord", searchWord);

        return "boardView/homework/homework";
    }

    //카테고리 선택 과제 리스트
    @ResponseBody
    @GetMapping("fetchList")
    public ArrayList<Homework> fetchList(@RequestParam String curriculum, @RequestParam String subject, @RequestParam String classname) {
        return service.fetchList(curriculum, subject, classname);
    }

    //과제 등록 폼
    @GetMapping("write")
    public String write(Model model
            , @AuthenticationPrincipal UserDetails user) {
        
        //학생이 과제 등록 접근 금지
        if (user.getAuthorities().stream().anyMatch(auth -> "ROLE_student".equals(auth.getAuthority()))) {
            return "redirect:/homework";
        }

        //카테고리
        ArrayList<HomeworkCategory> category = service.homeworkCategory();

        //커리큘럼 중복 제거
        List<String> curriculum = category.stream()
                .map(HomeworkCategory::getCurriculum)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("category", category);
        model.addAttribute("curriculum", curriculum);

        return "boardView/homework/write";
    }

    //과제 등록
    @PostMapping("write")
    public String write(Homework homework
            , @AuthenticationPrincipal UserDetails user
            , @RequestParam("upload") List<MultipartFile> uploads) {
        log.debug("과제: {}", homework);

        //datetime-local date타입으로 변환
        String start = homework.getHw_start().replace("T", " ");
        String end = homework.getHw_end().replace("T", " ");
        homework.setHw_start(start);
        homework.setHw_end(end);

        if (uploads != null && !uploads.isEmpty()) {
            List<String> savedFiles = new ArrayList<>();
            List<String> originalFiles = new ArrayList<>();

            for (MultipartFile upload : uploads) {
                if (!upload.isEmpty()) {
                    String savedfile = FileService.saveFile(upload, uploadPath);
                    savedFiles.add(savedfile);
                    originalFiles.add(upload.getOriginalFilename());
                }
            }

            homework.setOriginalfile(String.join(",", originalFiles));
            homework.setSavedfile(String.join(",", savedFiles));
        }

        homework.setMemberid(user.getUsername());
        service.insertHw(homework);

        return "redirect:/homework";
    }
    
    //과제 조회
    @GetMapping("read")
    public String read(@RequestParam(name="hw_num", defaultValue="0") int hw_num
            , @AuthenticationPrincipal UserDetails user
            , Model model) {

        //과제 내용 조회
        Homework hw = service.selectHw(hw_num);
        if(hw==null) {
            return "redirect:/homework";
        }
        model.addAttribute("hw", hw);

        //과제 제출 여부
        int subcount = service.subCount(user.getUsername(),hw.getHw_num());
        log.debug("subcount: {}",subcount);
        model.addAttribute("subcount", subcount);

        //과제 제출 리스트
        ArrayList<HomeworkSub> subList = service.subList(hw_num);
        log.debug("subList: {}",subList);
        model.addAttribute("subList", subList);

        //과제 해당 학생 리스트
        ArrayList<HomeworkStudent> student = service.stList(hw_num);
        log.debug("student: {}",student);
        model.addAttribute("student", student);

        return "boardView/homework/read";
    }

    //과제 파일 다운로드
    @GetMapping("download")
    public void download(
            @RequestParam("hw_num") int hw_num,
            @RequestParam("filename") String filename,
            HttpServletRequest request,
            HttpServletResponse response) {

        Homework hw = service.selectHw(hw_num);

        // 원래 파일 이름과 저장된 파일 이름 분리
        String[] originalFiles = hw.getOriginalfile().split(",");
        String[] savedFiles = hw.getSavedfile().split(",");

        String selectedSavedFile = null;
        for (int i = 0; i < originalFiles.length; i++) {
            if (originalFiles[i].equals(filename)) {
                selectedSavedFile = savedFiles[i];
                break;
            }
        }

        if (selectedSavedFile == null) {
            // 해당 파일이 없음을 처리 (예: 오류 메시지 반환)
            return;
        }

        String fullPath = uploadPath + "/" + selectedSavedFile;

        try {
            response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(filename, "UTF-8"));
            FileInputStream in = new FileInputStream(fullPath);
            ServletOutputStream out = response.getOutputStream();

            FileCopyUtils.copy(in, out);

            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //과제 수정 폼
    @GetMapping("update")
    public String update(@RequestParam(name="hw_num", defaultValue="0") int hw_num
            , Model model) {
        //카테고리
        ArrayList<HomeworkCategory> category = service.homeworkCategory();

        //커리큘럼 중복 제거
        List<String> curriculum = category.stream()
                .map(HomeworkCategory::getCurriculum)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("category", category);
        model.addAttribute("curriculum", curriculum);

        Homework hw = service.selectHw(hw_num);
        log.debug("수정할 과제: {}", hw);
        model.addAttribute("hw", hw);

        return "boardView/homework/update";
    }

    //과제 수정
    @PostMapping("update")
    public String update(Homework hw
            , @AuthenticationPrincipal UserDetails user
            , @RequestParam("upload") List<MultipartFile> uploads) {

        //datetime-local date타입으로 변환
        String start = hw.getHw_start().replace("T", " ");
        String end = hw.getHw_end().replace("T", " ");
        hw.setHw_start(start);
        hw.setHw_end(end);

        if (uploads != null && !uploads.isEmpty()) {
            List<String> savedFiles = new ArrayList<>();
            List<String> originalFiles = new ArrayList<>();

            for (MultipartFile upload : uploads) {
                if (!upload.isEmpty()) {
                    String savedfile = FileService.saveFile(upload, uploadPath);
                    savedFiles.add(savedfile);
                    originalFiles.add(upload.getOriginalFilename());
                }
            }

            hw.setOriginalfile(String.join(",", originalFiles));
            hw.setSavedfile(String.join(",", savedFiles));
        }

        hw.setMemberid(user.getUsername());
        service.updateHw(hw);

        return "redirect:/homework/read?hw_num=" + hw.getHw_num();
    }
    
    //과제 삭제
    @GetMapping("delete")
    public String delete(@RequestParam(name="hw_num", defaultValue="0") int hw_num
            , @AuthenticationPrincipal UserDetails user) {

        Homework hw = service.selectHw(hw_num);

        if(hw == null) {
            return "redirect:/homework";
        }
        if (hw.getSavedfile() != null && !hw.getSavedfile().isEmpty()) {
            // 쉼표를 기준으로 파일 이름들을 분리
            String[] savedFiles = hw.getSavedfile().split(",");

            for(String savedFile : savedFiles) {
                String fullPath = uploadPath + "/" + savedFile;
                FileService.deleteFile(fullPath);
            }
        }

        hw.setMemberid(user.getUsername());
        service.deleteHw(hw);

        return "redirect:/homework";
    }

    //과제 제출 폼
    @GetMapping("homeworkSub")
    public String homeworkSub(@RequestParam(name="hw_num", defaultValue="0") int hw_num, Model model) {

        model.addAttribute("hw_num", hw_num);

        return "boardView/homework/homeworkSub";
    }

    //과제 제출
    @PostMapping("insertSub")
    public String insertSub(HomeworkSub hw_sub
            , @AuthenticationPrincipal UserDetails user
            , @RequestParam("upload") List<MultipartFile> uploads) {

        hw_sub.setMemberid(user.getUsername());
        hw_sub.setHw_sub_done("제출완료");

        //과제 기한 처리
        Homework hw = service.selectHw(hw_sub.getHw_num());
        String hwStart = hw.getHw_start();
        String hwEnd = hw.getHw_end();

        log.debug("hwStart: {}", hwStart);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date hwStartDate = sdf.parse(hwStart);
            Date hwEndDate = sdf.parse(hwEnd);
            Date currentTime = new Date();

            if(currentTime.after(hwEndDate)) {
                hw_sub.setHw_sub_done("지각");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (uploads != null && !uploads.isEmpty()) {
            List<String> savedFiles = new ArrayList<>();
            List<String> originalFiles = new ArrayList<>();

            for (MultipartFile upload : uploads) {
                if (!upload.isEmpty()) {
                    String savedfile = FileService.saveFile(upload, uploadsubPath);
                    savedFiles.add(savedfile);
                    originalFiles.add(upload.getOriginalFilename());
                }
            }

            hw_sub.setOriginalfile(String.join(",", originalFiles));
            hw_sub.setSavedfile(String.join(",", savedFiles));
        }

        log.debug("저장할 과제: {}", hw_sub);

        service.insertSub(hw_sub);

        return "redirect:/homework/read?hw_num=" + hw_sub.getHw_num();
    }

    //제출 파일 다운로드
    @GetMapping("downloadSub")
    public void downloadSub(
            @RequestParam("hw_sub_num") int hw_sub_num,
            @RequestParam("filename") String filename,
            HttpServletRequest request,
            HttpServletResponse response) {

        HomeworkSub hw_sub = service.selectSub(hw_sub_num);

        // 원래 파일 이름과 저장된 파일 이름 분리
        String[] originalFiles = hw_sub.getOriginalfile().split(",");
        String[] savedFiles = hw_sub.getSavedfile().split(",");

        String selectedSavedFile = null;
        for (int i = 0; i < originalFiles.length; i++) {
            if (originalFiles[i].equals(filename)) {
                selectedSavedFile = savedFiles[i];
                break;
            }
        }

        if (selectedSavedFile == null) {
            // 해당 파일이 없음을 처리 (예: 오류 메시지 반환)
            return;
        }

        String fullPath = uploadsubPath + "/" + selectedSavedFile;

        try {
            response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(filename, "UTF-8"));
            FileInputStream in = new FileInputStream(fullPath);
            ServletOutputStream out = response.getOutputStream();

            FileCopyUtils.copy(in, out);

            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //제출 수정 폼
    @GetMapping("updateSub")
    public String updateSub(@RequestParam(name="hw_sub_num", defaultValue="0") int hw_sub_num
            , Model model) {

        HomeworkSub hw_sub = service.selectSub(hw_sub_num);
        log.debug("수정할 과제: {}", hw_sub);
        model.addAttribute("hw_sub", hw_sub);

        return "boardView/homework/updateSub";
    }

    //제출 수정
    @PostMapping("updateSub")
    public String updateSub(HomeworkSub hw_sub
            , @AuthenticationPrincipal UserDetails user
            , @RequestParam("upload") List<MultipartFile> uploads) {

        hw_sub.setMemberid(user.getUsername());
        hw_sub.setHw_sub_done("제출완료");

        //과제 기한 처리
        Homework hw = service.selectHw(hw_sub.getHw_num());
        String hwStart = hw.getHw_start();
        String hwEnd = hw.getHw_end();

        log.debug("hwStart: {}", hwStart);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date hwStartDate = sdf.parse(hwStart);
            Date hwEndDate = sdf.parse(hwEnd);
            Date currentTime = new Date();

            if(currentTime.after(hwEndDate)) {
                hw_sub.setHw_sub_done("지각");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (uploads != null && !uploads.isEmpty()) {
            List<String> savedFiles = new ArrayList<>();
            List<String> originalFiles = new ArrayList<>();

            for (MultipartFile upload : uploads) {
                if (!upload.isEmpty()) {
                    String savedfile = FileService.saveFile(upload, uploadsubPath);
                    savedFiles.add(savedfile);
                    originalFiles.add(upload.getOriginalFilename());
                }
            }

            hw_sub.setOriginalfile(String.join(",", originalFiles));
            hw_sub.setSavedfile(String.join(",", savedFiles));
        }

        service.updateSub(hw_sub);

        return "redirect:/homework/read?hw_num=" + hw_sub.getHw_num();
    }

    //제출 조회
    @GetMapping("readSub")
    public String readSub(@RequestParam(name="hw_sub_num", defaultValue="0") int hw_sub_num
            , @AuthenticationPrincipal UserDetails user
            , Model model) {

        //학생이 제출 조회 금지
        if (user.getAuthorities().stream().anyMatch(auth -> "ROLE_student".equals(auth.getAuthority()))) {
            return "redirect:/homework";
        }

        HomeworkSub hw_sub = service.selectSub(hw_sub_num);
        model.addAttribute("hw_sub", hw_sub);

        //과제 내용 조회
        Homework hw = service.selectHw(hw_sub.getHw_num());
        if(hw==null) {
            return "redirect:/homework";
        }
        model.addAttribute("hw", hw);

        //과제 해당 학생 리스트
        ArrayList<HomeworkStudent> student = service.stList(hw_sub.getHw_num());
        log.debug("student: {}",student);
        model.addAttribute("student", student);

        return "boardView/homework/readSub";
    }

    //점수 등록
    @PostMapping("updateScore")
    public String updateScore(@RequestParam(name="hw_sub_num", defaultValue="0") int hw_sub_num
            , @RequestParam(name="hw_sub_score", defaultValue="0") String hw_sub_score
            , @RequestParam(name="hw_sub_comment", defaultValue="") String hw_sub_comment
            , @AuthenticationPrincipal UserDetails user) {

        //학생이 점수 등록 금지
        if (user.getAuthorities().stream().anyMatch(auth -> "ROLE_student".equals(auth.getAuthority()))) {
            return "redirect:/homework";
        }

        HomeworkSub hw_sub = service.selectSub(hw_sub_num);
        hw_sub.setHw_sub_score(hw_sub_score);
        hw_sub.setHw_sub_comment(hw_sub_comment);

        service.updateScore(hw_sub);

        return "redirect:/homework/readSub?hw_sub_num=" + hw_sub_num;
    }

    //점수 일괄 등록
    @ResponseBody
    @PostMapping("updateSelectedScore")
    public void updateSelectedScore(
            @RequestParam("selectedHwSubNums") String[] hwSubNums,
            @RequestParam("hw_sub_score") String hw_sub_score,
            @RequestParam("hw_sub_comment") String hw_sub_comment) {

        log.debug("hwSubNums: {}", hwSubNums);

        for (String hwSubNum : hwSubNums) {
            int hw_sub_num = Integer.parseInt(hwSubNum);
            HomeworkSub hw_sub = service.selectSub(hw_sub_num);
            hw_sub.setHw_sub_score(hw_sub_score);
            hw_sub.setHw_sub_comment(hw_sub_comment);

            service.updateScore(hw_sub);
        }
        
    }

}