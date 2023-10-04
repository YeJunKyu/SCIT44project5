package com.scit.lms.service;

import com.scit.lms.dao.BoardDAO;
import com.scit.lms.domain.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service
@Slf4j
public class BoardServiceImple implements BoardService {

    @Autowired
    BoardDAO dao;

    @Override
    public int selectCount() {
        return dao.selectCount();
    }

    // 메인 공지 리스트
    @Override
    public ArrayList<Notice> noticeList() {
        return dao.noticeList();
    }

    // 학생 과정 불러오기
    @Override
    public String studentCurriculum(String username) {
        return dao.studentCurriculum(username);
    }

    // 메인 공지 리스트(학생)
    @Override
    public ArrayList<Notice> noticeListStudent(String curriculum) {
        return dao.noticeListStudent(curriculum);
    }

}
