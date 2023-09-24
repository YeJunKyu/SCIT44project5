package com.scit.lms.service;

import com.scit.lms.dao.QuestionDAO;
import com.scit.lms.domain.AnswerObject;
import com.scit.lms.domain.Option;
import com.scit.lms.domain.Question;
import com.scit.lms.domain.TestAnswer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionDAO dao;

    // 문제 등록
    @Override
    public int insertQuestion(Question question) {
        return dao.insertQuestion(question);
    }

    // 문제의 보기 및 정답 등록
    @Override
    public void insertOptions(List<Option> options) {
        dao.insertOptions(options);
    }

    // 문제 테이블의 정답 갱신
    @Override
    public void updateAnswer() {
        dao.updateAnswer();
    }

    // 시험지 문제 전체 조회
    @Override
    public ArrayList<Question> selectQuestions(int testnum) {
        return dao.selectQuestions(testnum);
    }

    // 문제에 해당하는 보기 조회
    @Override
    public ArrayList<Option> selectOptions(long qid) {
        return dao.selectOptions(qid);
    }

    // 문제 하나 조회
    @Override
    public Question selectOneQuestion(int qid) {
        return dao.selectOneQuestion(qid);
    }

    @Override
    public int deleteQuestion(int testid) {
        return dao.deleteQuestion(testid);
    }

    @Override
    public void updateQuestion(Question question) {
        dao.updateQuestion(question);
    }

    @Override
    public void updateOptions(List<Option> options) {
        dao.updateOptions(options);
    }

    @Override
    public int opidUp() {
        return dao.opidUp();
    }

    // 학생이 낸 시험지의 문제들
    @Override
    public void submitQuestion(TestAnswer testAnswer) {
        dao.submitQuestion(testAnswer);
    }


}
