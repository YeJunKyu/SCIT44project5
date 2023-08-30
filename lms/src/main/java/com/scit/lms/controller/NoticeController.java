package com.scit.lms.controller;

import com.scit.lms.domain.Board;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notice")
public class NoticeController {

    @PostMapping("write")
    public String write(Board board) {
//        log.debug("제목 : {}", board.getTitle());
//        log.debug("내용 : {}", board.getContents());
        //service.write(board);
        return "redirect:/notice";
    }
}
