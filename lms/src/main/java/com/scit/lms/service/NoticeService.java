package com.scit.lms.service;

import com.scit.lms.domain.Notice;
import com.scit.lms.util.PageNavigator;

import java.util.ArrayList;

public interface NoticeService {

    //공지 글 작성
    public void insert(Notice notice);

    //공지 글 목록
    public ArrayList<Notice> list(PageNavigator navi, String type, String searchWord);

    //공지 글 목록(학생용)
    public ArrayList<Notice> studentlist(PageNavigator navi, String type, String searchWord);

    //페이지네이션
    public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord);

    //공지 글 조회
    public Notice read(int noticenum);

    //공지 글 선택
    public Notice select(int noticenum);

    //공지 글 수정
    public void update(Notice notice);

    //공지 글 삭제
    public void delete(Notice notice);
}
