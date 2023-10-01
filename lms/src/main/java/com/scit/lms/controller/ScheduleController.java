package com.scit.lms.controller;

import com.scit.lms.domain.Schedule;
import com.scit.lms.domain.Student;
import com.scit.lms.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    // 최근 일정 목록
    @ResponseBody
    @GetMapping("recentList")
    public ArrayList<Schedule> recentList() {
        HashMap<String, String> map = new HashMap<>();

        // 현재 날짜 및 시간 얻기
        Date now = new Date();

        // 30일 후의 날짜를 얻기 위해 현재 날짜에 30일을 더함
        Date thirtyDaysLater = new Date(now.getTime() + (30L * 24 * 60 * 60 * 1000));

        // 날짜 포맷터 생성 (SQL 쿼리와 일치하도록)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 날짜를 문자열로 변환
        String start = sdf.format(now);
        String end = sdf.format(thirtyDaysLater);

        map.put("start", start);
        map.put("end", end);

        ArrayList<Schedule> list = service.recentList(map);
        log.debug("한달 스케줄 리스트: {}", list);

        return list;
    }

    // 카테고리 불러오기
    @ResponseBody
    @GetMapping("categoryList")
    public List<String> categoryList() {
        //카테고리 불러오기
        ArrayList<Student> category = service.categoryList();
        List<String> categories = category.stream()
                .map(Student::getCurriculum)
                .distinct()
                .collect(Collectors.toList());

        return categories;
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
        schedule.setCategories((String) eventData.get("categories"));

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
        schedule.setEventid((String) eventData.get("eventId"));
        schedule.setTitle((String) eventData.get("title"));
        schedule.setNote((String) eventData.get("description"));
        schedule.setStartdate((String) eventData.get("start"));
        schedule.setEnddate((String) eventData.get("end"));
        schedule.setMemberid(user.getUsername());
        schedule.setCategories((String) eventData.get("categories"));

        log.debug("수정할 스케줄: {}", schedule);
        service.update(schedule);
    }

}