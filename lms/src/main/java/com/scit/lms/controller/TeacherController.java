package com.scit.lms.controller;

import com.scit.lms.domain.Board;
import com.scit.lms.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    TeacherService  service;


    // 공지 글쓰기
    @PostMapping("write")
    public String write(Board board) {
//        log.debug("제목 : {}", board.getTitle());
//        log.debug("내용 : {}", board.getContents());
        service.write(board);
        return "redirect:/notice";
    }

    // 시험문제 등록
}
