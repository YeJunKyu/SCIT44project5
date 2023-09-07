package com.scit.lms.service;

import com.scit.lms.dao.ScheduleDAO;
import com.scit.lms.domain.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleDAO dao;

    // 일정 목록
    @Override
    public ArrayList<Schedule> list() {
        return dao.list();
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
}
