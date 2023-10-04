package com.scit.lms.controller;

import com.scit.lms.domain.Attendance;
import com.scit.lms.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//회원정보 관련 콘트롤러
@Slf4j
@Controller
@RequestMapping("data")
public class DataController {


    @Autowired
    DataService service;



    @ResponseBody
    @GetMapping("attendance")
    public Map<String, Integer> attendance(@AuthenticationPrincipal UserDetails user, Model model, Attendance attendance){
        // 사용자 정보를 기반으로 데이터를 가져오는 비즈니스 로직을 수행합니다.
        log.debug("아이디:{}",user.getUsername());
        String memberid = user.getUsername();
        attendance.setMemberid(memberid);
        int attendanceCount = service.countAttendance(attendance);
        int absenceCount = service.countAbsence(attendance);


        // 가져온 데이터를 기반으로 도넛 그래프 데이터를 생성합니다.
        // 이 예시에서는 출석, 결석, 기타 데이터를 사용합니다.
        Map<String, Integer> data = new HashMap<>();
        data.put("attendance", attendanceCount);
        data.put("absence", absenceCount);

        //data.put("기타", otherCount);

        // 모델에 도넛 그래프 데이터를 추가합니다.
        model.addAttribute("donutChartData", data);

        log.debug("데이터값{}", data);

        model.addAttribute("attendanceCount", attendanceCount);
        model.addAttribute("absenceCount", absenceCount);

        log.debug(String.valueOf(attendanceCount));


        // Thymeleaf 템플릿에서 모델에 추가한 도넛 그래프 데이터를 참조할 수 있습니다.
        return data; // Thymeleaf 템플릿의 경로를 반환합니다.
    }

    @ResponseBody
    @GetMapping("attendanceBar")
    public Map<String, String> attendanceBar(@AuthenticationPrincipal UserDetails user
                                , Model model, Attendance attendance) {
        String memberid = user.getUsername();
        attendance.setMemberid(memberid);
        log.debug(memberid);

        int att01 = service.attendance01(attendance);
        int abs01 = service.absence01(attendance);
        int att02 = service.attendance02(attendance);
        int abs02 = service.absence02(attendance);
        int att03 = service.attendance03(attendance);
        int abs03 = service.absence03(attendance);
        int att04 = service.attendance04(attendance);
        int abs04 = service.absence04(attendance);
        int att05 = service.attendance05(attendance);
        int abs05 = service.absence05(attendance);
        int att06 = service.attendance06(attendance);
        int abs06 = service.absence06(attendance);
        int att07 = service.attendance07(attendance);
        int abs07 = service.absence07(attendance);
        int att08 = service.attendance08(attendance);
        int abs08 = service.absence08(attendance);
        int att09 = service.attendance09(attendance);
        int abs09 = service.absence09(attendance);
        int att10 = service.attendance10(attendance);
        int abs10 = service.absence10(attendance);
        int att11 = service.attendance11(attendance);
        int abs11 = service.absence11(attendance);
        int att12 = service.attendance12(attendance);
        int abs12 = service.absence12(attendance);

        log.debug("10월출석:{}", att10);
        log.debug("10월결석:{}", abs10);
        Map<String, String> chartData = new HashMap<>();

        if (att01 + abs01 != 0) {
            double per01 = (double) att01 / (att01 + abs01)*100;
            chartData.put("bar01", String.valueOf(per01));
            log.debug("1월출석률:{}", per01);
        } else {
            chartData.put("bar01", "0");
        }
        if (att02 + abs02 != 0) {
            double per02 = (double) att02 / (att02 + abs02)*100;
            chartData.put("bar02", String.valueOf(per02));
            log.debug("2월출석률:{}", per02);
        } else {
            chartData.put("bar02", "0");
        }
        if (att03 + abs03 != 0) {
            double per03 = (double) att03 / (att03 + abs03)*100;
            chartData.put("bar03", String.valueOf(per03));
            log.debug("3월출석률:{}", per03);
        } else {
            chartData.put("bar03", "0");
        }
        if (att04 + abs04 != 0) {
            double per04 = (double) att04 / (att04 + abs04)*100;
            chartData.put("bar04", String.valueOf(per04));
            log.debug("4월출석률:{}", per04);
        } else {
            chartData.put("bar10", "0");
        }
        if (att05 + abs05 != 0) {
            double per05 = (double) att05 / (att05 + abs05)*100;
            chartData.put("bar05", String.valueOf(per05));
            log.debug("5월출석률:{}", per05);
        } else {
            chartData.put("bar05", "0");
        }
        if (att06 + abs06 != 0) {
            double per06 = (double) att06 / (att06 + abs06)*100;
            chartData.put("bar06", String.valueOf(per06));
            log.debug("6월출석률:{}", per06);
        } else {
            chartData.put("bar06", "0");
        }
        if (att07 + abs07 != 0) {
            double per07 = (double) att07 / (att07 + abs07)*100;
            chartData.put("bar07", String.valueOf(per07));
            log.debug("7월출석률:{}", per07);
        } else {
            chartData.put("bar07", "0");
        }
        if (att08 + abs08 != 0) {
            double per08 = (double) att08 / (att08 + abs08)*100;
            chartData.put("bar08", String.valueOf(per08));
            log.debug("8월출석률:{}", per08);
        } else {
            chartData.put("bar08", "0");
        }
        if (att09 + abs09 != 0) {
            double per09 = (double) att09 / (att09 + abs09)*100;
            chartData.put("bar09", String.valueOf(per09));
            log.debug("9월출석률:{}", per09);
        } else {
            chartData.put("bar09", "0");
        }
        if (att10 + abs10 != 0) {
            double per10 = (double) att10 / (att10 + abs10)*100;
            chartData.put("bar10", String.valueOf(per10));
            log.debug("10월출석률:{}", per10);
        } else {
            chartData.put("bar10", "0");
        }
        if (att11 + abs11 != 0) {
            double per11 = (double) att11 / (att11 + abs11)*100;
            chartData.put("bar11", String.valueOf(per11));
            log.debug("11월출석률:{}", per11);
        } else {
            chartData.put("bar11", "0");
        }
        if (att12 + abs12 != 0) {
            double per12 = (double) att12 / (att12 + abs12)*100;
            chartData.put("bar12", String.valueOf(per12));
            log.debug("12월출석률:{}", per12);
        } else {
            chartData.put("bar12", "0");
        }
        log.debug("totototototo:{}",chartData);

        return chartData;
    }

}
