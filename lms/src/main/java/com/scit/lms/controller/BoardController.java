package com.scit.lms.controller;

import com.scit.lms.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    // 학생 관리 페이지 이동
    @GetMapping("manage")
    public String manage() {
        return "boardView/manage/manage";
    }

    // 강의 페이지 이동
    @GetMapping("lecture")
    public String lecture() {
        return "boardView/lecture/lecture";
    }



    @GetMapping("sidebar")
    public String sidebar(Model model){
        log.debug("인증수1:{}","확인");
        int selectCount = service.selectCount();
        log.debug("인증수2:{}",selectCount);
        model.addAttribute("countUser",selectCount);
        return "fragments/sidebar";
    }
}
