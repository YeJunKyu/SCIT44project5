package com.scit.lms.service;

import com.scit.lms.domain.Question;

import java.util.ArrayList;

public interface QuestionBankService {
    // 모든 문제 조회
    ArrayList<Question> selectAllQuestions();
}
