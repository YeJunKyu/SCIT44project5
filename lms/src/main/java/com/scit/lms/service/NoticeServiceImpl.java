package com.scit.lms.service;

import com.scit.lms.dao.NoticeDAO;
import com.scit.lms.domain.Notice;
import com.scit.lms.domain.Student;
import com.scit.lms.util.PageNavigator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Transactional
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeDAO dao;


    @Override
    public ArrayList<Student> selectCg() {
        return dao.selectCg();
    }

    //공지 글 작성
    @Override
    public void insert(Notice notice) {
        dao.insert(notice);
    }

    //공지 글 목록
    @Override
    public ArrayList<Notice> list(PageNavigator navi, String type, String searchWord) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("searchWord", searchWord);

        RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());
        return dao.list(map, rb);
    }

    //공지 글 목록(학생용)
    @Override
    public ArrayList<Notice> studentlist(PageNavigator navi, String type, String searchWord) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("searchWord", searchWord);

        RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());
        return dao.studentlist(map, rb);
    }

    //페이지네이션
    @Override
    public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord) {
        //검색할 정보
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("searchWord", searchWord);

        //글 수(검색 포함)
        int total = dao.getTotal(map);

        //페이지 수, 글 수
        PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);

        return navi;
    }

    //페이지네이션(학생)
    @Override
    public PageNavigator getPageNavigatorStudent(int pagePerGroup, int countPerPage, int page, String type, String searchWord) {
        //검색할 정보
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("searchWord", searchWord);

        //글 수(검색 포함)
        int total = dao.getStudentTotal(map);

        //페이지 수, 글 수
        PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);

        return navi;
    }

    //공지 글 조회
    @Override
    public Notice read(int noticenum) {
        //조회수 증가
        dao.updateHits(noticenum);
        Notice notice = dao.read(noticenum);
        return notice;
    }

    //공지 글 선택
    @Override
    public Notice select(int noticenum) {
        Notice notice = dao.read(noticenum);
        return notice;
    }

    @Override
    public void update(Notice notice) {
        dao.update(notice);
    }

    //공지 글 삭제
    @Override
    public void delete(Notice notice) {
        dao.delete(notice);
    }

}
