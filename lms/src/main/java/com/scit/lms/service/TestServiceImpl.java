package com.scit.lms.service;

import com.scit.lms.dao.TestDAO;
import com.scit.lms.domain.Test;
import com.scit.lms.domain.TestpaperList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TestServiceImpl implements TestService{

    @Autowired
    TestDAO dao;

    // 테스트 목록 조회
    @Override
    public ArrayList<Test> testListAll() {
        return dao.testListAll();
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
    public int submitTest(TestpaperList testpaperList) {
        int asnum = dao.submitTest(testpaperList);
        log.debug("서비스 asnum : {}", asnum);
        return asnum;
    }

    //시험 하나의 제출된 답변들 목록으로 이동
    @Override
    public List<TestpaperList> testList(int testid) {
        return dao.testList(testid);
    }

    //testid 가져오기
    @Override
    public int getTestid(int asnum) {
        return dao.getTestid(asnum);
    }

    // 제출된 답변 총점 계산
    @Override
    public void updateTotalpoints(int asnum) {
        dao.updateTotalpoints(asnum);
    }
}
