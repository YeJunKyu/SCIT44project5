package com.scit.lms.controller;

import com.scit.lms.domain.Notice;
import com.scit.lms.util.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import com.scit.lms.domain.Member;
import com.scit.lms.service.MemberService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.thymeleaf.spring5.context.IThymeleafBindStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//회원정보 관련 콘트롤러

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    MemberService service;

    @Value("${member.servlet.multipart.location}")
    String uploadPath;

    //회원가입 폼으로 이동
    @GetMapping("join")
    public String join() {

        return "memberView/join";
    }


    //회원가입 처리
    @PostMapping("join")
    public String join(Member member
            , @RequestParam("postcode") String postcode
            , @RequestParam("inputAddress") String inputAddress
            , @RequestParam("detailAddress") String detailAddress) {

        String fullAddress = "(" + postcode + ") " + inputAddress + " " + detailAddress;
        log.debug("주소:{}", fullAddress);
        member.setAddress(fullAddress);

        log.debug("회원1:{}", member);
        int n = service.join(member);
        log.debug("회원2:{}", member);
        return "redirect:/";
    }

    //id중복확인 폼
    @GetMapping("idcheck")
    public String idcheck() {
        return "memberView/idcheck";
    }

    //id중복 확인 처리
    @PostMapping("idcheck")
    public String idcheck(String searchid, Model model) {
        log.debug("중복체크1:{}", searchid);
        boolean result = service.idcheck(searchid);
        log.debug("중복체크2:{}", searchid);
        model.addAttribute("searchid", searchid);
        model.addAttribute("result", result);

        return "memberView/idcheck";
    }

    //아이디 중복확인(2)
    @ResponseBody
    @PostMapping("checkMemberid")
    public Map<String, String> checkMemberid(Member member, Model model){
        int result = service.checkid(member);
        Map<String, String> response = new HashMap<>();
        log.debug("가져온정보: {}", member);
        if(result == 1){
            response.put("result", String.valueOf(result));
            response.put("msg", "아이디가 중복되었습니다.");
            return response;
        } else {
            response.put("result", String.valueOf(result));
            response.put("msg", "사용가능한 아이디입니다.");
            return response;
        }
    }

    //로그인 폼으로 이동
    @GetMapping("login")
    public String login() {
        return "memberView/login";

    }
    //회원정보 불러오기
    @GetMapping("information")
    public String information(@AuthenticationPrincipal UserDetails user, Model model){
        //로그인한 아이디로 회원정보 검색
        Member member = service.memberInfor(user.getUsername());


        //검색결과 모델에 저장
        model.addAttribute("user", member);
        log.debug("인포메이션멤버{}", member);
        return "memberView/memberInfo";
    }

    //회원정보 수정폼으로 이동
    @GetMapping("updateForm")
    public String updateForm(@AuthenticationPrincipal UserDetails user, Model model){
        Member member = service.memberInfor(user.getUsername());


        //검색결과 모델에 저장
        model.addAttribute("user", member);
        log.debug("** param :{}", member);
        return "memberView/updateForm";
    }

    //회원정보 수정
    @PostMapping("memberUpdate")
    public String memberUpdate(@AuthenticationPrincipal UserDetails user, Model model, Member member
            , MultipartFile upload
            , ResourceHandlerRegistry registry){


        int n = service.memberUpdate(member);
        log.debug("11111111{}", member);
        Member m = service.memberInfor(user.getUsername());

        //검색결과 모델에 저장
        model.addAttribute("user", m);



//멤버사진 업데이트



        log.debug("dddddddddddddddddddddddddddddddddddddddd");
        log.debug("멀티파트파일:{}", upload);
        log.debug("멤버:{}", member);
        log.debug("이름이름이름{}", member.getMembername());

        if(member.getMemberphoto() != null && !member.getMemberphoto().isEmpty() && upload != null && !upload.isEmpty()) {
            String fullPath = uploadPath + "/" + member.getMemberphoto();
            FileService.deleteFile(fullPath);
        }

        if (upload != null && !upload.isEmpty()) {
            String savedfile=FileService.saveFile(upload, uploadPath);
            member.setMemberphoto(upload.getOriginalFilename());
            member.setMemberphoto(savedfile);

        }

        log.debug("업로드", upload);
        log.debug("업로드패스", uploadPath);
        member.setMemberid(user.getUsername());
        log.debug("멤버2:{}", member);

        service.memberphoto(member);
        model.addAttribute("user", member);


        return "memberView/memberInfo";
    }

    //비밀번호 확인
    @GetMapping("changePassword")
    public String checkPassword(){
        return "memberView/changePassword";
    }

    //비밀번호 변경
    @ResponseBody
    @PostMapping("changePassword")
    public Map<String, String> changePassword(Member member, Model model, @AuthenticationPrincipal UserDetails user){
        boolean result = service.pwcheck(member);
        Map<String, String> response = new HashMap<>();
        log.debug("가져온정보: {}", member);
        if(result){
/*            model.addAttribute("msg", "확인되었습니다.");
            model.addAttribute("password", user.getPassword());
            model.addAttribute("result", result);
            return "memberView/changePassword"; */
            response.put("password", member.getPassword());
            response.put("result", String.valueOf(result));
            response.put("msg", "확인되었습니다.");
            return response;
        } else {
/*            model.addAttribute("msg", "비밀번호가 틀렸습니다.");
            model.addAttribute("password", "");
            model.addAttribute("result", result);
            return "memberView/changePassword";   */
            response.put("password", "");
            response.put("result", String.valueOf(result));
            response.put("msg", "비밀번호가 틀렸습니다.");
            return response;
        }
    }



    //사진변경폼으로 이동

    @GetMapping("insertPhoto")
    public String insertPhoto() {
        return "memberView/insertPhoto";
    }

    //사진 변경하기
    @PostMapping("updatePhoto")
    public String update(Member member, @AuthenticationPrincipal UserDetails user
            , MultipartFile upload, Model model) {

        log.debug("dddddddddddddddddddddddddddddddddddddddd");
        log.debug("멀티파트파일:{}", upload);
        log.debug("멤버:{}", member);
        log.debug("이름이름이름{}", member.getMembername());

        if(member.getMemberphoto() != null && !member.getMemberphoto().isEmpty() && upload != null && !upload.isEmpty()) {
            String fullPath = uploadPath + "/" + member.getMemberphoto();
            FileService.deleteFile(fullPath);
        }

        if (upload != null && !upload.isEmpty()) {
            String savedfile=FileService.saveFile(upload, uploadPath);
            member.setMemberphoto(savedfile);
            member.setPhotopath(uploadPath);


        }


        member.setMemberid(user.getUsername());
        log.debug("멤버2:{}", member);

        log.debug("업로드: {}", upload);
        log.debug("업로드패스: {}", uploadPath);

        service.memberphoto(member);
        model.addAttribute("user", member);


        Member m = service.memberInfor(user.getUsername());
        //검색결과 모델에 저장
        model.addAttribute("user", m);

        return "memberView/memberInfo";
    }

    //회원 탈퇴전 비밀번호확인 폼으로 이동
    @GetMapping("deleteMember")
    public String deleteMember() {
        return "memberView/deleteMember";
    }


    //비밀번호 (변경전) 확인
    @PostMapping("checkPassword")
    public String checkPassword(Member member, Model model, @AuthenticationPrincipal UserDetails user) {
        log.debug("!!!!:{}", member);
        boolean result = service.pwcheck(member);
        log.debug("????:{}", member);

        log.debug("확인결과:{}", result);

        if(result){
            model.addAttribute("msg", "확인되었습니다.");
            model.addAttribute("result", result);
            return "memberView/deleteMember";
        } else {
            model.addAttribute("msg", "비밀번호가 틀렸습니다.");
            model.addAttribute("result", result);
            return "memberView/deleteMember";
        }
    }

    //멤버삭제
    @GetMapping("goDeleteMember")
    public String deleteMember(Member member, @AuthenticationPrincipal UserDetails user){
        member.setMemberid(user.getUsername());

        log.debug("멤버삭제하러감:{}", member);
        int n = service.deleteMember(member);

        return "redirect:/";
    }

    //비밀번호 변경
    @PostMapping("changePW")
    public String changePW(@RequestParam("newPWCheck") String newPassword, Member member
                        , @AuthenticationPrincipal UserDetails user){
        member.setMemberid(user.getUsername());
        member.setMemberpw(newPassword);
        log.debug("새비번:{}", member);

        int n = service.changePW(member);




        return "redirect:/";
    }


}
