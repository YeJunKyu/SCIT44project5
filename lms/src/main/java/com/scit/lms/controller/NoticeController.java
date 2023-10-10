package com.scit.lms.controller;

import com.scit.lms.domain.HomeworkCategory;
import com.scit.lms.domain.Notice;
import com.scit.lms.domain.Student;
import com.scit.lms.service.NoticeService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    NoticeService service;

    //파일 저장경로
    @Value("${notice.servlet.multipart.location}")
    String uploadPath;

    //게시판 목록의 페이지당 글 수
    @Value("${user.board.page}")
    int countPerPage;

    //게시판 목록의 페이지 이동 링크 수
    @Value("${user.board.group}")
    int pagePerGroup;

    //공지 글 목록
    @GetMapping({"","/"})
    public String notice(Model model
            , String type
            , String searchWord
            , @AuthenticationPrincipal UserDetails user
            , @RequestParam (name="page", defaultValue="1") int page) {

        //학생은 전체 공지만 조회
        if (user.getAuthorities().stream().anyMatch(auth -> "ROLE_student".equals(auth.getAuthority()))) {
            PageNavigator navi = service.getPageNavigatorStudent(pagePerGroup, countPerPage, page, type, searchWord);

            ArrayList<Notice> list = service.studentlist(navi, type, searchWord);

            model.addAttribute("list", list);
            model.addAttribute("navi", navi);
            model.addAttribute("type", type);
            model.addAttribute("searchWord", searchWord);

            return "boardView/notice/notice";
        }

        PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);

        ArrayList<Notice> list = service.list(navi, type, searchWord);

        model.addAttribute("list", list);
        model.addAttribute("navi", navi);
        model.addAttribute("type", type);
        model.addAttribute("searchWord", searchWord);

        return "boardView/notice/notice";
    }

    //공지 글 작성 폼
    @GetMapping("write")
    public String write(@AuthenticationPrincipal UserDetails user,
                        Model model) {

        //학생이 공지 등록 접근 금지
        if (user.getAuthorities().stream().anyMatch(auth -> "ROLE_student".equals(auth.getAuthority()))) {
            return "redirect:/notice";
        }

        //카테고리 불러오기
        ArrayList<Student> category = service.categoryList();
        List<String> categories = category.stream()
                .map(Student::getCurriculum)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("categories", categories);

        return "boardView/notice/write";
    }

    //공지 글 작성
    @PostMapping("write")
    public String write(
            @AuthenticationPrincipal UserDetails user,
            Notice notice,
            @RequestParam("upload") List<MultipartFile> uploads) {

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

            notice.setOriginalfile(String.join(",", originalFiles));
            notice.setSavedfile(String.join(",", savedFiles));
        }

        notice.setMemberid(user.getUsername());
        service.insert(notice);

        return "redirect:/notice";
    }

    //공지 글 조회
    @GetMapping("read")
    public String read(@RequestParam(name="noticenum", defaultValue="0") int noticenum
            , @AuthenticationPrincipal UserDetails user
            , Model model) {

        Notice notice = service.read(noticenum);

        if(notice==null) {
            return "redirect:/notice";
        }

        //학생이 선생용 공지 접근 금지
        if (user.getAuthorities().stream().anyMatch(auth -> "ROLE_student".equals(auth.getAuthority()))) {
            if(notice.getViewauthority()==1) {
                return "redirect:/notice";
            }
        }

        model.addAttribute("notice", notice);

        return "boardView/notice/read";
    }

    //파일 다운로드
    @GetMapping("download")
    public void download(
            @RequestParam("noticenum") int noticenum,
            @RequestParam("filename") String filename,
            HttpServletRequest request,
            HttpServletResponse response) {

        Notice notice = service.select(noticenum);

        // 원래 파일 이름과 저장된 파일 이름 분리
        String[] originalFiles = notice.getOriginalfile().split(",");
        String[] savedFiles = notice.getSavedfile().split(",");

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

    //공지 글 수정 폼
    @GetMapping("update")
    public String update(@RequestParam(name="noticenum", defaultValue="0") int noticenum
            , Model model) {

        //수정할 공지 글 불러오기
        Notice notice = service.select(noticenum);
        model.addAttribute("notice", notice);

        //카테고리 불러오기
        ArrayList<Student> category = service.categoryList();
        List<String> categories = category.stream()
                .map(Student::getCurriculum)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("categories", categories);

        return "boardView/notice/update";
    }

    //공지 글 수정
    @PostMapping("update")
    public String update(Notice notice
            , @AuthenticationPrincipal UserDetails user
            , @RequestParam("upload") List<MultipartFile> uploads) {

        log.debug("공지: {}", notice);

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

            notice.setOriginalfile(String.join(",", originalFiles));
            notice.setSavedfile(String.join(",", savedFiles));
        }

        notice.setMemberid(user.getUsername());
        service.update(notice);

        return "redirect:/notice/read?noticenum=" + notice.getNoticenum();
    }

    //공지 글 삭제
    @GetMapping("delete")
    public String delete(@RequestParam(name="noticenum", defaultValue="0") int noticenum
            ,@AuthenticationPrincipal UserDetails user) {

        Notice notice = service.read(noticenum);

        if(notice == null) {
            return "redirect:/notice";
        }
        if (notice.getSavedfile() != null && !notice.getSavedfile().isEmpty()) {
            // 쉼표를 기준으로 파일 이름들을 분리
            String[] savedFiles = notice.getSavedfile().split(",");

            for(String savedFile : savedFiles) {
                String fullPath = uploadPath + "/" + savedFile;
                FileService.deleteFile(fullPath);
            }
        }

        notice.setMemberid(user.getUsername());
        service.delete(notice);

        return "redirect:/notice";
    }

}
