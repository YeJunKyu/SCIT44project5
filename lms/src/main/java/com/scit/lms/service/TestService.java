package com.scit.lms.service;

import com.scit.lms.domain.Test;
import com.scit.lms.domain.TestpaperList;

import java.util.ArrayList;
import java.util.List;

public interface TestService {

    // 테스트 목록 조회
    ArrayList<Test> testListAll();

    // 테스트 등록
    int insertTest(Test test);

    // 테스트 정보 조회
    Test selectTest(int testid);

    int deleteTest(int testid);

    int updateTest(Test test);

    // 학생이 낸 시험지
    int submitTest(TestpaperList testpaperList);

    //시험 하나의 제출된 답변들 목록으로 이동
    List<TestpaperList> testList(int testid);

    //testid　가져오기
    int getTestid(int asnum);

    // 제출된 답변 총점 계산
    void updateTotalpoints(int asnum);


}
