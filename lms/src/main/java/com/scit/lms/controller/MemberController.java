package com.scit.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import com.scit.lms.domain.Member;
import com.scit.lms.service.MemberService;

//회원정보 관련 콘트롤러

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    MemberService service;

    //회원가입 폼으로 이동
    @GetMapping("join")
    public String join() {

        return "memberView/join";
    }


    //회원가입 처리
    @PostMapping("join")
    public String join(Member member) {
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
        log.debug("{}", member);
        return "memberView/memberInfo";
    }

    //회원정보 수정폼으로 이동
    @GetMapping("updateForm")
    public String updateForm(@AuthenticationPrincipal UserDetails user, Model model){
        Member member = service.memberInfor(user.getUsername());


        //검색결과 모델에 저장
        model.addAttribute("user", member);
        log.debug("{}", member);
        return "memberView/updateForm";
    }

    @PostMapping("memberUpdate")
    public String memberUpdate(@AuthenticationPrincipal UserDetails user, Model model, Member member){
        int n = service.memberUpdate(member);
        log.debug("11111111{}", member);
        Member m = service.memberInfor(user.getUsername());
        //검색결과 모델에 저장
        model.addAttribute("user", m);
        return "memberView/memberInfo";
    }
}
