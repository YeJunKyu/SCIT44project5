package com.scit.lms.service;

import com.scit.lms.domain.Option;
import com.scit.lms.domain.Question;
import com.scit.lms.domain.TestAnswer;

import java.util.ArrayList;
import java.util.List;

public interface QuestionService {
    // 문제 등록
    int insertQuestion(Question question);

    // 문제의 보기 및 정답 등록
    void insertOptions(List<Option> options);

    // 문제 테이블에 정답 갱신
    void updateAnswer();

    // 시험지 문제 전체 조회
    ArrayList<Question> selectQuestions(int testnum);

    // 문제에 해당하는 보기 조회
    ArrayList<Option> selectOptions(long qid);

    // 문제 하나 조회
    Question selectOneQuestion(int qid);

    int deleteQuestion(int testid);

    void updateQuestion(Question question);

    void updateOptions(List<Option> options);

    int opidUp();

    // 학생이 낸 시험지의 문제들
    void submitQuestion(TestAnswer TestAnswer);
}
