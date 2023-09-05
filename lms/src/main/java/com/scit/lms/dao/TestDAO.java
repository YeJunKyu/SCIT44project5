package com.scit.lms.dao;

import com.scit.lms.domain.Question;
import com.scit.lms.domain.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TestDAO {

    // 테스트 목록 조회
    ArrayList<Test> testList();

    // 테스트 등록
    void submitTest(Test test);
    // 문제 등록
//    void submitQuestion(Question question);
}
