package com.scit.lms.controller;

import com.scit.lms.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("schedule")
public class ScheduleController {

    //@Autowired
    //NoticeService service;

    @GetMapping({"","/"})
    public String notice() {
        return "boardView/schedule/schedule";
    }
}
