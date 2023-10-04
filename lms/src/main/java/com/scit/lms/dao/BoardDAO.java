package com.scit.lms.dao;

import com.scit.lms.domain.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BoardDAO {
    int selectCount();

    // 메인 공지 리스트
    public ArrayList<Notice> noticeList();

    // 학생 커리큘럼
    public String studentCurriculum(String username);

    // 메인 공지 리스트(학생)
    public ArrayList<Notice> noticeListStudent(String curriculum);
}
