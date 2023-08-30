package com.scit.lms.controller;

import com.scit.lms.domain.Member;
import com.scit.lms.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    BoardService service;

    // 메인 화면
    @GetMapping("main")
    public String main() {

        return "boardView/main";
    }

    // 공지 페이지 이동
    @GetMapping("notice")
    public String notice() {
        return "boardView/notice/notice";
    }

    // 공지 조회부분
    @GetMapping("notice/read")
    public String readNotice() {
        return "";
    }
    // 글쓰기 폼 이동
    @GetMapping("notice/write")
    public String noticeWrite() {
        return "boardView/notice/write";
    }

    // 학생 관리 페이지 이동
    @GetMapping("manage")
    public String manage() {
        return "boardView/manage/manage";
    }




    // 과제 페이지 이동
    @GetMapping("homework")
    public String homework() {
        return "boardView/homework/homework";
    }

    // 강의 페이지 이동
    @GetMapping("lecture")
    public String lecture() {
        return "boardView/lecture/lecture";
    }

    // 문제은행 페이지 이동
    @GetMapping("questionBank")
    public String questionBank() {
        return "boardView/questionBank/questionBank";
    }
    
    //사이드바 경로
    @GetMapping("sidebar")
    public String sidebar(Model model
                          ){
        // 회원인증폼 일반사용자회원 수 조회(회원인증알림)
        log.debug("인증수1:{}","확인");
        int selectCount = service.selectCount();
        log.debug("인증수2:{}",selectCount);
        model.addAttribute("countUser",selectCount);

        return "fragments/sidebar :: sidebarFrag";
    }


}
