package com.scit.lms.dao;

import com.scit.lms.domain.AnswerObject;
import com.scit.lms.domain.Option;
import com.scit.lms.domain.Question;
import com.scit.lms.domain.TestAnswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface QuestionDAO {
    // 문제 등록
    int insertQuestion(Question question);

    // 문제의 보기 및 정답 등록
    void insertOptions(List<Option> options);

    // 문제 테이블의 정답 갱신
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
    void submitQuestion(TestAnswer testAnswer);

    // 채점을 위한 문제 가져오기
    Question getQuestionByQid(int qid);

    // 점수 업데이트
    void updatePoints(TestAnswer updateAnswer);

    // 선택한 답 가져오기
    ArrayList<TestAnswer> getAllTestAnswers(int asnum);
}
