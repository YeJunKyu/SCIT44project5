package com.scit.lms.service;

import com.scit.lms.dao.QuestionDAO;
import com.scit.lms.domain.Option;
import com.scit.lms.domain.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionDAO dao;

    // 문제 등록
    @Override
    public int submitQuestion(Question question) {
        return dao.submitQuestion(question);
    }

    // 문제의 보기 및 정답 등록
    @Override
    public void submitOptions(List<Option> options) {
        dao.submitOptions(options);
    }
}
