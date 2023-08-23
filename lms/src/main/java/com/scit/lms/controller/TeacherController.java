package com.scit.lms.controller;

import com.scit.lms.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class TeacherController {

    @Autowired
    TeacherService  service;

}
