package com.scit.lms.service;

import com.scit.lms.domain.Schedule;

import java.util.ArrayList;

public interface ScheduleService {
    // 일정 목록 불러오기
    public ArrayList<Schedule> list();

    // 일정 저장
    public void insert(Schedule schedule);

    // 일정 삭제
    public void delete(Schedule schedule);

    // 일정 수정
    public void update(Schedule schedule);

    // 일정 선택
    public Schedule select(String eventid);
}
