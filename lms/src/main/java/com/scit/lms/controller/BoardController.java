package com.scit.lms.controller;

import com.scit.lms.domain.Notice;
import com.scit.lms.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    BoardService service;

    // 메인 화면
    @GetMapping("main")
    public String main(Model model, @AuthenticationPrincipal UserDetails user) {
        ArrayList<Notice> noticeList;
        noticeList = service.noticeList();

        //학생은 학생 공지, 자기 과정 공지만 보이게
        if (user.getAuthorities().stream().anyMatch(auth -> "ROLE_student".equals(auth.getAuthority()))) {
            String curriculum = service.studentCurriculum(user.getUsername());
            noticeList = service.noticeListStudent(curriculum);
        }

        log.debug("공지 리스트: {}", noticeList);
        model.addAttribute("noticeList", noticeList);
        return "boardView/main";
    }

    @GetMapping("sidebar")
    public String sidebar(Model model){
        log.debug("인증수1:{}","확인");
        int selectCount = service.selectCount();
        log.debug("인증수2:{}",selectCount);

        // 오늘 날짜 추가하기
        LocalDate today = LocalDate.now();
        model.addAttribute("today", today);
        model.addAttribute("countUser",selectCount);
        return "fragments/sidebar";
    }
}
