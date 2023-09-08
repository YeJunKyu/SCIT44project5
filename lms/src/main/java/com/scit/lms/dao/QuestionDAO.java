package com.scit.lms.dao;

import com.scit.lms.domain.Option;
import com.scit.lms.domain.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionDAO {
    // 문제 등록
    int submitQuestion(Question question);

    // 문제의 보기 및 정답 등록
    void submitOptions(List<Option> options);
}
