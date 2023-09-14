package com.scit.lms.service;

import com.scit.lms.domain.*;

import java.util.ArrayList;

public interface HomeworkService {
    //학생 리스트 불러오기
    public ArrayList<Student> studentList();

    //일본어 분반 불러오기
    public ArrayList<JP_category> jpClassList();

    //IT 분반 불러오기
    public ArrayList<IT_category> itClassList();

    //과제 등록
    public void insertHw(Homework homework);

    //과제 리스트
    public ArrayList<Homework> hwList();

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
}
