package com.scit.lms.dao;

import com.scit.lms.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface HomeworkDAO {
    //과제 등록
    public void insertHw(Homework hw);

    //학생 이름, 커리큘럼, 일본어 반, IT반 불러오기
    public ArrayList<HomeworkCategory> homeworkCategory();

    //과제 리스트
    public ArrayList<Homework> hwList(HashMap<String, String> map, RowBounds rb);

    //현재 로그인한 학생 카테고리 불러오기
    public HomeworkCategory studentCategory(String memberid);

    //현재 로그인한 학생 과제 리스트 불러오기
    public ArrayList<Homework> studentHwList(HashMap<String, String> map, RowBounds rb);

    //전체 글 개수
    public int getTotal(HashMap<String, String> map);

    //전체 글 개수(학생)
    public int getStudentTotal(HashMap<String, String> map);

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
    public int subCount(@Param("memberid") String memberid, @Param("hw_num") int hw_num);

    //제출 조회
    public HomeworkSub selectSub(int hw_sub_num);

    //제출 수정
    public void updateSub(HomeworkSub hw_sub);

    //학생 리스트
    public ArrayList<HomeworkStudent> stList(int hw_num);

    //점수 등록
    public void updateScore(HomeworkSub hwSub);

    //카테고리 선택 과제 리스트
    public ArrayList<Homework> fetchList(@Param("curriculum") String curriculum, @Param("subject") String subject, @Param("classname") String classname);
}
