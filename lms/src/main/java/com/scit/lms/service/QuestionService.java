package com.scit.lms.service;

import com.scit.lms.domain.Option;
import com.scit.lms.domain.Question;

import java.util.List;

public interface QuestionService {
    // 문제 등록
    int submitQuestion(Question question);

    // 문제의 보기 및 정답 등록
    void submitOptions(List<Option> options);
}
