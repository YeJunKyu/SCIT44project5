package com.scit.lms.controller;

import com.scit.lms.domain.Question;
import com.scit.lms.service.QuestionBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("questionBank")
@Controller
@Slf4j
public class QuestionBankController {

    @Autowired
    QuestionBankService service;


    // 문제은행 페이지 이동
    @GetMapping("")
    public String questionBank(Model model) {
        ArrayList<Question> questions = service.selectAllQuestions();
        model.addAttribute("questions", questions);
        return "boardView/questionBank/questionBank";
    }


    @PostMapping("delete")
    public String deleteQuestionBankItems(@RequestBody Map<String, Object> requestMap) {
        List<String> selectedItems = (List<String>) requestMap.get("selectedItems");
        log.debug("{}", selectedItems);

        return "";
    }
}
