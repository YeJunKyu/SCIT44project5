package com.scit.lms.service;

import com.scit.lms.dao.ScheduleDAO;
import com.scit.lms.domain.Schedule;
import com.scit.lms.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleDAO dao;

    // 카테고리 목록
    @Override
    public ArrayList<Student> categoryList() {
        return dao.categoryList();
    }

    // 일정 목록
    @Override
    public ArrayList<Schedule> list() {
        return dao.list();
    }

    // 최근 일정 목록
    @Override
    public ArrayList<Schedule> recentList(HashMap<String, String> map) {
        return dao.recentList(map);
    }

    // 일정 등록
    @Override
    public void insert(Schedule schedule) {
        dao.insert(schedule);
    }

    // 일정 삭제
    @Override
    public void delete(Schedule schedule) {
        dao.delete(schedule);
    }

    // 일정 수정
    @Override
    public void update(Schedule schedule) {
        dao.update(schedule);
    }

    // 일정 선택
    @Override
    public Schedule select(String eventid) {
        return dao.select(eventid);
    }

    // 일정 목록(학생)
    @Override
    public ArrayList<Schedule> listStudent(String curriculum) {
        return dao.listStudent(curriculum);
    }

    @Override
    public String studentCurriculum(String username) {
        return dao.studentCurriculum(username);
    }
}
