package com.scit.lms.service;

import com.scit.lms.domain.Attendance;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public interface DataService {


    int countAttendance(Attendance attendance);

    int countAbsence(Attendance attendance);

    String attendanceDate(Attendance attendance);

    int attendance01(Attendance attendance);
    int attendance02(Attendance attendance);
    int attendance03(Attendance attendance);
    int attendance04(Attendance attendance);
    int attendance05(Attendance attendance);
    int attendance06(Attendance attendance);
    int attendance07(Attendance attendance);
    int attendance08(Attendance attendance);
    int attendance09(Attendance attendance);
    int attendance10(Attendance attendance);
    int attendance11(Attendance attendance);
    int attendance12(Attendance attendance);

    int absence01(Attendance attendance);
    int absence02(Attendance attendance);
    int absence03(Attendance attendance);
    int absence04(Attendance attendance);
    int absence05(Attendance attendance);
    int absence06(Attendance attendance);
    int absence07(Attendance attendance);
    int absence08(Attendance attendance);
    int absence09(Attendance attendance);
    int absence10(Attendance attendance);
    int absence11(Attendance attendance);
    int absence12(Attendance attendance);

}
