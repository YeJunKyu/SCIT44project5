package com.scit.lms.dao;

import com.scit.lms.domain.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ScheduleDAO {
    // 일정 목록
    public ArrayList<Schedule> list();

    // 일정 등록
    public void insert(Schedule schedule);

    // 일정 삭제
    public void delete(Schedule schedule);

    // 일정 수정
    public void update(Schedule schedule);

    // 일정 선택
    public Schedule select(String eventid);
}
