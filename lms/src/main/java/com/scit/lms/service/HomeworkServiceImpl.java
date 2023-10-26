package com.scit.lms.service;

import com.scit.lms.dao.HomeworkDAO;
import com.scit.lms.domain.*;
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
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    HomeworkDAO dao;

    //과제 등록
    @Override
    public void insertHw(Homework hw) {
        dao.insertHw(hw);
    }

    //학생 이름, 커리큘럼, 일본어 반, IT반 불러오기
    @Override
    public ArrayList<HomeworkCategory> homeworkCategory() {
        return dao.homeworkCategory();
    }

    //과제 리스트
    @Override
    public ArrayList<Homework> hwList(PageNavigator navi, String type, String searchWord) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("searchWord", searchWord);

        RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
        return dao.hwList(map, rb);
    }

    //현재 로그인한 학생 카테고리 불러오기
    @Override
    public HomeworkCategory studentCategory(String memberid) {
        return dao.studentCategory(memberid);
    }

    //현재 로그인한 학생 과제 리스트 불러오기
    @Override
    public ArrayList<Homework> studentHwList(HomeworkCategory studentCategory, PageNavigator navi, String type, String searchWord) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("searchWord", searchWord);
        map.put("curriculum", studentCategory.getCurriculum());
        map.put("jpclassname", studentCategory.getJpclassname());
        map.put("itclassname", studentCategory.getItclassname());

        RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());

        return dao.studentHwList(map, rb);
    }

    //페이지네이션
    @Override
    public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord) {
        //검색할 정보
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("searchWord", searchWord);

        int total = dao.getTotal(map);

        //페이지 수, 글 수
        PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);

        return navi;
    }

    //페이지네이션(학생)
    @Override
    public PageNavigator getPageNavigatorStudent(int pagePerGroup, int countPerPage, int page, String type, String searchWord, HomeworkCategory studentCategory) {
        //검색할 정보
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("searchWord", searchWord);
        map.put("curriculum", studentCategory.getCurriculum());
        map.put("jpclassname", studentCategory.getJpclassname());
        map.put("itclassname", studentCategory.getItclassname());

        int total = dao.getStudentTotal(map);

        //페이지 수, 글 수
        PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);

        return navi;
    }

    //과제 조회
    @Override
    public Homework selectHw(int hw_num) {
        return dao.selectHw(hw_num);
    }

    //과제 수정
    @Override
    public void updateHw(Homework hw) {
        dao.updateHw(hw);
    }

    //과제 삭제
    @Override
    public void deleteHw(Homework hw) {
        dao.deleteHw(hw);
    }

    //과제 제출
    @Override
    public void insertSub(HomeworkSub hw_sub) {
        dao.insertSub(hw_sub);
    }

    //제출 리스트
    @Override
    public ArrayList<HomeworkSub> subList(int hw_num) {
        return dao.subList(hw_num);
    }

    //제출 카운트
    @Override
    public int subCount(String memberid, int hw_num) {
        return dao.subCount(memberid, hw_num);
    }

    //제출 조회
    @Override
    public HomeworkSub selectSub(int hw_sub_num) {
        return dao.selectSub(hw_sub_num);
    }

    //제출 수정
    @Override
    public void updateSub(HomeworkSub hw_sub) {
        dao.updateSub(hw_sub);
    }

    //학생 리스트
    @Override
    public ArrayList<HomeworkStudent> stList(int hw_num) {
        return dao.stList(hw_num);
    }

    //점수 등록
    @Override
    public void updateScore(HomeworkSub hwSub) {
        dao.updateScore(hwSub);
    }

    //카테고리 선택 과제 리스트
    @Override
    public ArrayList<Homework> fetchList(String curriculum, String subject, String classname) {
        return dao.fetchList(curriculum, subject, classname);
    }

}
