package com.scit.lms.controller;

import com.scit.lms.domain.Option;
import com.scit.lms.domain.Question;
import com.scit.lms.domain.Test;
import com.scit.lms.domain.TestRequestObject;
import com.scit.lms.service.QuestionService;
import com.scit.lms.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("test")
@Slf4j
public class TestController {
    @Autowired
    TestService testService;

    @Autowired
    QuestionService questionService;

    // 시험 페이지 이동
    @GetMapping("")
    public String test(Model model) {
//        ArrayList<Test> test = service.testList();
////
//        model.addAttribute("test", test);
        return "boardView/test/test";
    }
    // 시험 문제 생성
    @GetMapping("create")
    public String createTestForm() {
        return "boardView/test/createTest";
    }

    // 시험 문제 등록
    @PostMapping("submitTest")
    public String submitTest(@RequestBody TestRequestObject requestObject) {
        String testname = requestObject.getTestname();
        String testdate = requestObject.getTestdate().replace("T", " ");
        String testlimit = requestObject.getTestlimit().replace("T", " ");

        Test test = new Test();
        test.setTestname(testname);
        test.setTestdate(testdate);
        test.setTestlimit(testlimit);
        int testid = testService.submitTest(test);
        log.debug("testID : {}", testid);
        
        Question[] questions = requestObject.getQuestionDataArray();

        for (Question q: questions) {
            Question question = new Question();
            question.setTestid(test.getTestid());
            question.setContents(q.getContents());
            question.setPoints(q.getPoints());
            question.setType(q.getType());
            int qid = questionService.submitQuestion(question);
            log.debug("qid : {}", qid);

            List<Option> options = new ArrayList<>();
            for (Option optionData: q.getOptions()) {
                Option option = new Option();
                option.setQid(question.getQid());
                option.setValue(optionData.getValue());
                option.setContent(optionData.getContent());
                option.setChecked(optionData.getChecked());
                options.add(option);
            }
            log.debug("qid : {}, options : {}", question.getQid(), options);

            questionService.submitOptions(options);
        }

        return "boardView/test/test";
    }
}
