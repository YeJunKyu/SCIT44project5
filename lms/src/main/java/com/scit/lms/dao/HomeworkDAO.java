package com.scit.lms.dao;

import com.scit.lms.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface HomeworkDAO {
    //학생 리스트 불러오기
    public ArrayList<Student> studentList();

    //일본어 분반 불러오기
    public ArrayList<JP_category> jpClassList();

    //IT 분반 불러오기
    public ArrayList<IT_category> itClassList();

    //과제 등록
    public void insertHw(Homework hw);

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
    public int subCount(@Param("memberid") String memberid, @Param("hw_num") int hw_num);

    //제출 조회
    public HomeworkSub selectSub(int hw_sub_num);

    //제출 수정
    public void updateSub(HomeworkSub hw_sub);

    //학생 리스트
    public ArrayList<HomeworkStudent> stList(int hw_num);
}
