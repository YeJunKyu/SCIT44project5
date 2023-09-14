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
        ArrayList<Test> test = testService.testList();
        model.addAttribute("test", test);

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

        // 시험 등록
        Test test = new Test();
        test.setTestname(testname);
        test.setTestdate(testdate);
        test.setTestlimit(testlimit);
        int testid = testService.submitTest(test);
//        log.debug("testID : {}", testid);

        // 문제 배열 추출
        Question[] questions = requestObject.getQuestionDataArray();

        // 문제 배열 등록
        for (Question q: questions) {
            Question question = new Question();
            question.setTestid(test.getTestid());
            question.setPapernum(q.getPapernum());
            question.setContents(q.getContents());
            question.setPoints(q.getPoints());
            question.setType(q.getType());
            questionService.submitQuestion(question);


            // 타입이 1, 2, 3인 경우만 option 및 answer 등록
            if(q.getOptions() != null && !q.getOptions().isEmpty() && (q.getType() == 1 || q.getType() == 2 || q.getType() == 3)) {
                List<Option> options = new ArrayList<>();
                for (Option optionData: q.getOptions()) {
                    if(optionData.getValue() != null) {
                        Option option = new Option();
                        option.setQid(question.getQid());
                        option.setValue(optionData.getValue());
                        option.setContent(optionData.getContent());
                        option.setChecked(optionData.getChecked());
//                        log.debug("옵션 {}, {}, {}, {}", option.getQid(), option.getValue(), option.getContent(), option.getChecked());
                        options.add(option);
                    }
                }
//                log.debug("qid : {}, options : {}", question.getQid(), options);

                // optoin 등록
                questionService.submitOptions(options);

                // answer 갱신
                questionService.updateAnswer();

            }
        }
        return "boardView/test/test";
    }

    // 시험지 문제 전체 조회
    @GetMapping("viewTest")
    public String viewTest(@RequestParam("testid") int testid, Model model) {
        // 시험 정보
        Test test = testService.selectTest(testid);

        // 시험의 문제
        ArrayList<Question> questions = questionService.selectQuestions(testid);

        // 객관식 유형 문제의 보기
        ArrayList<Option> allOptions = new ArrayList<>();
        for (Question q : questions) {
            int qType = q.getType();
            if(qType == 1 || qType == 2) {
                ArrayList<Option> options = questionService.selectOptions(q.getQid());
                allOptions.addAll(options);
            }
        }
        model.addAttribute("test", test);
        model.addAttribute("questions", questions);
        model.addAttribute("options", allOptions);

        return "boardView/test/viewTest";
    }
}
