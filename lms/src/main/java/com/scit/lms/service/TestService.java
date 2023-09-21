package com.scit.lms.service;

import com.scit.lms.domain.Question;
import com.scit.lms.domain.Test;

import java.util.ArrayList;

public interface TestService {

    // 테스트 목록 조회
    ArrayList<Test> testList();

    // 테스트 등록
    int insertTest(Test test);

    // 테스트 정보 조회
    Test selectTest(int testid);
}
