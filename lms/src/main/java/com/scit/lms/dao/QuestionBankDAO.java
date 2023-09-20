package com.scit.lms.dao;

import com.scit.lms.domain.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface QuestionBankDAO {
    // 모든 문제 조회
    ArrayList<Question> selectAll();
}
