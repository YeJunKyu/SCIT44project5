package com.scit.lms.service;

import com.scit.lms.dao.TestDAO;
import com.scit.lms.domain.Question;
import com.scit.lms.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestDAO dao;

    // 테스트 목록 조회
    @Override
    public ArrayList<Test> testList() {
        return dao.testList();
    }

    // 테스트 등록
    @Override
    public void submitTest(Test test, Question question) {
        dao.submitTest(test);
        dao.submitQuestion(question);
    }
}
