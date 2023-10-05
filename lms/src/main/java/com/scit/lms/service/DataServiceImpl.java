package com.scit.lms.service;

import com.scit.lms.dao.DataDAO;
import com.scit.lms.domain.Attendance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DataServiceImpl implements DataService{

    @Autowired
    DataDAO dao;


    @Override
    public int countAttendance(Attendance attendance) {
        int n = dao.countAttendance(attendance);

        return n;
    }

    @Override
    public int countAbsence(Attendance attendance) {
        int n = dao.countAbsence(attendance);

        return n;
    }
    @Override
    public String attendanceDate(Attendance attendance){
        String attendanceDate = dao.attendanceDate(attendance);

        return attendanceDate;
    }

    @Override
    public int attendance01(Attendance attendance){
        int attendance01 = dao.attendance01(attendance);
        return attendance01;
    }
    @Override
    public int attendance02(Attendance attendance){
        int attendance02 = dao.attendance02(attendance);
        return attendance02;
    }
    @Override
    public int attendance03(Attendance attendance){
        int attendance03 = dao.attendance03(attendance);
        return attendance03;
    }
    @Override
    public int attendance04(Attendance attendance){
        int attendance04 = dao.attendance04(attendance);
        return attendance04;
    }
    @Override
    public int attendance05(Attendance attendance){
        int attendance05 = dao.attendance05(attendance);
        return attendance05;
    }
    @Override
    public int attendance06(Attendance attendance){
        int attendance06 = dao.attendance06(attendance);
        return attendance06;
    }
    @Override
    public int attendance07(Attendance attendance){
        int attendance07 = dao.attendance07(attendance);
        return attendance07;
    }
    @Override
    public int attendance08(Attendance attendance){
        int attendance08 = dao.attendance08(attendance);
        return attendance08;
    }
    @Override
    public int attendance09(Attendance attendance){
        int attendance09 = dao.attendance09(attendance);
        return attendance09;
    }
    @Override
    public int attendance10(Attendance attendance){
        int attendance10 = dao.attendance10(attendance);
        return attendance10;
    }
    @Override
    public int attendance11(Attendance attendance){
        int attendance11 = dao.attendance11(attendance);
        return attendance11;
    }
    @Override
    public int attendance12(Attendance attendance){
        int attendance12 = dao.attendance12(attendance);
        return attendance12;
    }
    @Override
    public int absence01(Attendance attendance){
        int absence01 = dao.absence01(attendance);
        return absence01;
    }
    @Override
    public int absence02(Attendance attendance){
        int absence02 = dao.absence02(attendance);
        return absence02;
    }
    @Override
    public int absence03(Attendance attendance){
        int absence03 = dao.absence03(attendance);
        return absence03;
    }
    @Override
    public int absence04(Attendance attendance){
        int absence04 = dao.absence04(attendance);
        return absence04;
    }
    @Override
    public int absence05(Attendance attendance){
        int absence05 = dao.absence05(attendance);
        return absence05;
    }
    @Override
    public int absence06(Attendance attendance){
        int absence06 = dao.absence06(attendance);
        return absence06;
    }
    @Override
    public int absence07(Attendance attendance){
        int absence07 = dao.absence07(attendance);
        return absence07;
    }
    @Override
    public int absence08(Attendance attendance){
        int absence08 = dao.absence08(attendance);
        return absence08;
    }
    @Override
    public int absence09(Attendance attendance){
        int absence09 = dao.absence09(attendance);
        return absence09;
    }
    @Override
    public int absence10(Attendance attendance){
        int absence10 = dao.absence10(attendance);
        return absence10;
    }
    @Override
    public int absence11(Attendance attendance){
        int absence11 = dao.absence11(attendance);
        return absence11;
    }
    @Override
    public int absence12(Attendance attendance){
        int absence12 = dao.absence12(attendance);
        return absence12;
    }

}
