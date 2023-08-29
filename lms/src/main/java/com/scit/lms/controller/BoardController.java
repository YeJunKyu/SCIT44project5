package com.scit.lms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class BoardController {

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

    // 시험 페이지 이동
    @GetMapping("test")
    public String test() {
        return "boardView/test/test";
    }
    // 시험 문제 생성
    @GetMapping("test/create")
    public String createTestForm() {
        return "boardView/test/createTest";
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


}
