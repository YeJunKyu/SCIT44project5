package com.scit.lms.service;

import com.scit.lms.dao.QuestionBankDAO;
import com.scit.lms.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionBankServiceImpl implements QuestionBankService{

    @Autowired
    QuestionBankDAO dao;

    @Override
    public ArrayList<Question> selectAllQuestions() {
        return dao.selectAll();
    }
}
