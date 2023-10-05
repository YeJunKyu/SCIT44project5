package com.scit.lms.service;

import com.scit.lms.dao.TestDAO;
import com.scit.lms.domain.AllOfTest;
import com.scit.lms.domain.PrimaryRatio;
import com.scit.lms.domain.Test;
import com.scit.lms.domain.TestpaperList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public ArrayList<PrimaryRatio> selectCategory() {
        return dao.selectCategory();
    }

    @Override
    public int updateCategory(PrimaryRatio primaryRatio) {
        return dao.updateCategory(primaryRatio);
    }

    // 선택한 답변 정보 가져오기
    @Override
    public TestpaperList getTestpaperList(int asnum) {
        return dao.getTestpaperList(asnum);
    }

    // 제출된 응답 확인 상태 변경
    @Override
    public void completeCheck(int asnum) {
        dao.completeCheck(asnum);
    }

    // 정답지 번호 가져오기
    @Override
    public int getAsnum(String answernum) {
        return dao.getAsnum(answernum);
    }

    // 학생이 본인 성적 확인
    @Override
    public ArrayList<Test> selectTestByStudent(String username) {
        return dao.selectTestByStudent(username);
    }

    @Override
    public ArrayList<AllOfTest> submitTestList(String memberid) {
        return dao.submitTestList(memberid);
    }

    @Override
    public TestpaperList checkDuplicateSubmission(TestpaperList testpaperList) {
        return dao.checkDuplicateSubmission(testpaperList);
    }

    @Override
    public ArrayList<PrimaryRatio> secondaryCategory(int primaryId) {
        return dao.secondaryCategory(primaryId);
    }

    @Override
    public ArrayList<TestpaperList> getTestpaperListByCategoryId(Map<String, String> map) {
        return dao.getTestpaperListByCategoryId(map);
    }

    // 키워드로 시험 찾기
    @Override
    public ArrayList<TestpaperList> searchByKeyword(Map<String, String> map) {
        ArrayList<TestpaperList> t = dao.searchByKeyword(map);
        log.debug("서비스단 리스트:{}", t);
        return t;
    }

}
