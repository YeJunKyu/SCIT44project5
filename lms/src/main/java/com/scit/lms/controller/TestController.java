package com.scit.lms.controller;

import com.scit.lms.domain.Question;
import com.scit.lms.domain.Test;
import com.scit.lms.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("test")
@Slf4j
public class TestController {
    @Autowired
    TestService service;

    // 시험 페이지 이동
    @GetMapping("")
    public String test(Model model) {
        ArrayList<Test> test = service.testList();

        model.addAttribute("test", test);
        return "boardView/test/test";
    }
    // 시험 문제 생성
    @GetMapping("create")
    public String createTestForm() {
        return "boardView/test/createTest";
    }

    // 시험 문제 등록
    @PostMapping("submit")
    public String submitTest(Test test, Question question) {
        service.submitTest(test, question);
        return "redirect:/test";
    }
}
