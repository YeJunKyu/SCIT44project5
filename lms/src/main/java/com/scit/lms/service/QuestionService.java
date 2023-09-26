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
    Question selectOneQuestion(long qid);

    int deleteQuestion(int testid);

    void updateQuestion(Question question);

    void updateOptions(Option option);

    int opidUp();

    // 학생이 낸 시험지의 문제들
    void submitQuestion(TestAnswer TestAnswer);

    int countQuestion(int testid);

    int countOption(Integer integer);

    int countQidOption(Long aLong);
    // 채점을 위한 문제 가져오기
    Question getQuestionByQid(int qid);

    // 점수 업데이트
    void updatePoints(TestAnswer updateAnswer);

    // 선택한 답 가져오기
    ArrayList<TestAnswer> getAllTestAnswers(int asnum);

    // 문제 타입 가져오기
    int getQuestionType(int qid);

    // 제출된 시험지 문제 하나 선택
    TestAnswer selectOneAnswer(int answernum);
}
