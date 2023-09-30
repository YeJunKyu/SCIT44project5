package com.scit.lms.dao;

import com.scit.lms.domain.Notice;
import com.scit.lms.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface NoticeDAO {

    //과정 불러오기
    public ArrayList<Student> selectCg();

    //공지 글 작성
    public int insert(Notice notice);

    //공지 글 목록
    public ArrayList<Notice> list(HashMap<String, String> map, RowBounds rb);

    //공지 글 목록(학생용)
    public ArrayList<Notice> studentlist(HashMap<String, String> map, RowBounds rb);

    //전체 글 개수
    public int getTotal(HashMap<String, String> map);

    //전체 글 개수
    public int getStudentTotal(HashMap<String, String> map);

    //공지 글 조회
    public Notice read(int noticenum);

    //공지 글 조회수 증가
    public void updateHits(int noticenum);

    //공지 글 삭제
    public void delete(Notice notice);

    //공지 글 수정
    public void update(Notice notice);

}
