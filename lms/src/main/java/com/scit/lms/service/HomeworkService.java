package com.scit.lms.service;

import com.scit.lms.domain.*;
import com.scit.lms.util.PageNavigator;

import java.util.ArrayList;

public interface HomeworkService {
    //과제 등록
    public void insertHw(Homework homework);

    //학생 이름, 커리큘럼, 일본어 반, IT반 불러오기
    public ArrayList<HomeworkCategory> homeworkCategory();

    //과제 리스트
    public ArrayList<Homework> hwList(PageNavigator navi, String type, String searchWord);

    //현재 로그인한 학생 카테고리 불러오기
    public HomeworkCategory studentCategory(String memberid);

    //현재 로그인한 학생 과제 리스트 불러오기
    public ArrayList<Homework> studentHwList(HomeworkCategory studentCategory, PageNavigator navi, String type, String searchWord);

    //페이지네이션
    public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord);

    //페이지네이션(학생)
    public PageNavigator getPageNavigatorStudent(int pagePerGroup, int countPerPage, int page, String type, String searchWord, HomeworkCategory studentCategory);

    //과제 조회
    public Homework selectHw(int hw_num);

    //과제 수정
    public void updateHw(Homework hw);

    //과제 삭제
    public void deleteHw(Homework hw);

    //과제 제출
    public void insertSub(HomeworkSub hw_sub);

    //제출 리스트
    public ArrayList<HomeworkSub> subList(int hw_num);

    //제출 카운트
    public int subCount(String memberid, int hw_num);

    //제출 조회
    public HomeworkSub selectSub(int hw_sub_num);

    //제출 수정
    public void updateSub(HomeworkSub hwSub);

    //학생 리스트
    public ArrayList<HomeworkStudent> stList(int hwNum);

    //점수 등록
    public void updateScore(HomeworkSub hwSub);

    //카테고리 선택 과제 리스트
    public ArrayList<Homework> fetchList(String curriculum, String subject, String classname);
}
