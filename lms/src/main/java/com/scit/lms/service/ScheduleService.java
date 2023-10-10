package com.scit.lms.service;

import com.scit.lms.domain.Schedule;
import com.scit.lms.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;

public interface ScheduleService {
    // 카테고리 목록 불러오기
    public ArrayList<Student> categoryList();

    // 일정 목록 불러오기
    public ArrayList<Schedule> list();

    // 최근 일정 목록
    public ArrayList<Schedule> recentList(HashMap<String, String> map);

    // 일정 저장
    public void insert(Schedule schedule);

    // 일정 삭제
    public void delete(Schedule schedule);

    // 일정 수정
    public void update(Schedule schedule);

    // 일정 선택
    public Schedule select(String eventid);

    // 일정 목록(학생)
    public ArrayList<Schedule> listStudent(String curriculum);

    // 학생 커리큘럼
    public String studentCurriculum(String username);
}
