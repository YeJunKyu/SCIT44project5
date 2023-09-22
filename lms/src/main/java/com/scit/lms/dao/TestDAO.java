package com.scit.lms.dao;

import com.scit.lms.domain.Test;
import com.scit.lms.domain.TestListFromStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TestDAO {

    // 테스트 목록 조회
    ArrayList<Test> testList();

    // 테스트 등록
    int insertTest(Test test);

    // 테스트 정보 조회
    Test selectTest(int testid);

    int deleteTest(int testid);

    int updateTest(Test test);

    // 학생이 낸 시험지
    int submitTest(TestListFromStudent tlfs);
}
