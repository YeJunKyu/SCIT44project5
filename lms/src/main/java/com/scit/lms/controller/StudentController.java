package com.scit.lms.controller;

import com.scit.lms.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("teacher")
public class StudentController {

    @Autowired
    StudentService service;

}