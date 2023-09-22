package com.scit.lms.service;

import com.scit.lms.dao.TestDAO;
import com.scit.lms.domain.Test;
import com.scit.lms.domain.TestListFromStudent;
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
    public int insertTest(Test test) {
        return dao.insertTest(test);
    }

    // 테스트 정보 조회
    @Override
    public Test selectTest(int testid) {
        return dao.selectTest(testid);
    }

    @Override
    public int deleteTest(int testid) {
        return dao.deleteTest(testid);
    }

    @Override
    public int updateTest(Test test) {
        return dao.updateTest(test);
    }

    // 학생이 낸 시험지
    @Override
    public int submitTest(TestListFromStudent tlfs) {
        return dao.submitTest(tlfs);
    }
}
