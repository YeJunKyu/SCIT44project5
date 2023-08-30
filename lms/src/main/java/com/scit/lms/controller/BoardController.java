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


}
