package com.scit.lms.service;

import com.scit.lms.dao.HomeworkDAO;
import com.scit.lms.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Transactional
@Service
public class HomeworkServiceImpl implements HomeworkService{
    
    @Autowired
    HomeworkDAO dao;

    //학생 리스트 불러오기
    @Override
    public ArrayList<Student> studentList() {
        return dao.studentList();
    }

    //일본어 분반 불러오기
    @Override
    public ArrayList<JP_category> jpClassList() {
        return dao.jpClassList();
    }

    //IT 분반 불러오기
    @Override
    public ArrayList<IT_category> itClassList() {
        return dao.itClassList();
    }

    //과제 등록
    @Override
    public void insertHw(Homework hw) {
        dao.insertHw(hw);
    }

    //과제 리스트
    @Override
    public ArrayList<Homework> hwList() {
        return dao.hwList();
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

    @Override
    public HomeworkSub selectSub(int hw_sub_num) {
        return dao.selectSub(hw_sub_num);
    }

    //제출 삭제
    @Override
    public void updateSub(HomeworkSub hw_sub) {
        dao.updateSub(hw_sub);
    }

    //학생 리스트
    @Override
    public ArrayList<HomeworkStudent> stList(int hw_num) {
        return dao.stList(hw_num);
    }


}
