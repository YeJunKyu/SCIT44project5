package com.scit.lms.controller;

import com.scit.lms.domain.Schedule;
import com.scit.lms.service.NoticeService;
import com.scit.lms.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    ScheduleService service;

    @GetMapping({"", "/"})
    public String schedule() {
        return "boardView/schedule/schedule";
    }

    // 일정 목록 불러오기
    @ResponseBody
    @GetMapping("list")
    public ArrayList<Schedule> list() {
        ArrayList<Schedule> list = service.list();
        log.debug("스케줄 리스트: {}", list);
        return list;
    }

    // 일정 저장
    @ResponseBody
    @PostMapping("insert")
    public void insert(@RequestBody Map<String, Object> eventData,
                       @AuthenticationPrincipal UserDetails user) {
        log.debug("등록할 eventData: {}", eventData);

        Schedule schedule = new Schedule();

        //하루종일 체크
        Boolean allDay = (Boolean) eventData.get("allDay");
        if (allDay != null && allDay) {
            schedule.setAllday(true);
            schedule.setColor("");
            schedule.setTextcolor("");
        } else {
            schedule.setAllday(false);
            schedule.setColor("");
            schedule.setTextcolor("");
        }

        //eventData를 schedule에 set
        schedule.setTitle((String) eventData.get("title"));
        schedule.setNote((String) eventData.get("description"));
        schedule.setStartdate((String) eventData.get("start"));
        schedule.setEnddate((String) eventData.get("end"));
        schedule.setMemberid(user.getUsername());

        log.debug("등록할 스케줄: {}", schedule);
        service.insert(schedule);
    }

    // 일정 삭제
    @ResponseBody
    @PostMapping("delete")
    public void delete(String eventid, @AuthenticationPrincipal UserDetails user) {
        log.debug("eventid: {}", eventid);
        log.debug("user: {}", user);
        Schedule schedule = service.select(eventid);
        schedule.setMemberid(user.getUsername());
        log.debug("삭제할 schedule: {}", schedule);
        service.delete(schedule);
    }

    // 일정 수정
    @ResponseBody
    @PostMapping("update")
    public void update(@RequestBody Map<String, Object> eventData,
                       @AuthenticationPrincipal UserDetails user) {
        log.debug("수정할 eventData: {}", eventData);

        Schedule schedule = new Schedule();

        //하루종일 체크
        Boolean allDay = (Boolean) eventData.get("allDay");
        if (allDay != null && allDay) {
            schedule.setAllday(true);
            schedule.setColor("");
            schedule.setTextcolor("");
        } else {
            schedule.setAllday(false);
            schedule.setColor("");
            schedule.setTextcolor("");
        }

        //eventData를 schedule에 set
        schedule.setTitle((String) eventData.get("title"));
        schedule.setNote((String) eventData.get("description"));
        schedule.setStartdate((String) eventData.get("start"));
        schedule.setEnddate((String) eventData.get("end"));
        schedule.setMemberid(user.getUsername());

        log.debug("수정할 스케줄: {}", schedule);
        service.update(schedule);
    }

}