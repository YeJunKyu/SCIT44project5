package com.scit.lms.dao;

import com.scit.lms.domain.Schedule;
import com.scit.lms.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface ScheduleDAO {
    // 카테고리 목록
    public ArrayList<Student> categoryList();

    // 일정 목록
    public ArrayList<Schedule> list();

    // 최근 일정 목록
    public ArrayList<Schedule> recentList(HashMap<String, String> map);

    // 일정 등록
    public void insert(Schedule schedule);

    // 일정 삭제
    public void delete(Schedule schedule);

    // 일정 수정
    public void update(Schedule schedule);

    // 일정 선택
    public Schedule select(String eventid);
}
