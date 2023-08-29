package com.scit.lms.service;

import com.scit.lms.dao.TeacherDAO;
import com.scit.lms.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherDAO dao;

    // 공지 글쓰기
    @Override
    public void write(Board board) {
        dao.writeNotice(board);
    }
}
