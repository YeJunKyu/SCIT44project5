package com.scit.lms.service;

import com.scit.lms.domain.Notice;

import java.util.ArrayList;

public interface BoardService {

    int selectCount();

    // 메인 공지 리스트
    public ArrayList<Notice> noticeList();

    // 학생 과정 불러오기
    public String studentCurriculum(String username);

    // 메인 공지 리스트(학생)
    public ArrayList<Notice> noticeListStudent(String curriculum);
}
