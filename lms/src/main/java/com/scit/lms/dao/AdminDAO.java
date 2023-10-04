package com.scit.lms.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.scit.lms.domain.*;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdminDAO {


    ArrayList<Member> selectAll();



    void update(Member member);

    ArrayList<StudentsAll> selectAllStudents();

    ArrayList<StudentsAll> selectOnlyStudent();

    void insertCurriculum(ArrayList<Student> students);

    ArrayList<StudentsAll> selectOnlyStudentClass();

    void insertClass(ArrayList<StudentClasses> studentsAllClasses);

    ArrayList<Member> selectOnlyStudentInformation();

    void insertInformation(ArrayList<StudentInfo> studentAllInfos);

    ArrayList<PrimaryRatio> AllRatios();

    void insertBigRatio(PrimaryRatio primaryRatio);

    PrimaryRatio selectOneCategory(String categoryname);

    ArrayList<PrimaryRatio> ReadBigRatio();

    void InsertMiddleRatio(PrimaryRatio primaryRatio);

    ArrayList<PrimaryRatio> ReadMiddleRatio();

    ArrayList<PrimaryRatio> ReadTestBigList();

    ArrayList<PrimaryRatio> ReadTestMiddleList();

    ArrayList<PrimaryRatio> ReadTestSmallList();

    void updateTestList(PrimaryRatio primaryRatio);


    void InsertStudentAttendance(Attendance attendances);

    ArrayList<StudentsAll> selectAllAttendance(String att_date);

    ArrayList<StudentsAll> ReadStudentAttendance();

    ArrayList<StudentsAll> SelectDateAttendance(String selectedDate);

    void UpdateStudentAttendance(Attendance attendance);

    StudentsAll ReadOneStudent(String memberid);

    void updateInformation(StudentsAll studentsAll);

    void updateitClass(StudentsAll studentsAll);

    void updatejpClass(StudentsAll studentsAll);

    void updateCurriculum(StudentsAll studentsAll);

    void insertOneCurriculum(StudentsAll studentsAll);

    void insertOneitClass(StudentsAll studentsAll);

    void insertOnejpClass(StudentsAll studentsAll);

    void insertOneInformation(StudentsAll studentsAll);

    Student selectOneStudent(String memberid);

    JP_category selectOnejpCategory(String memberid);

    IT_category selectOneitCategory(String memberid);

    StudentInfo selectOneStudentInfo(String memberid);

    ArrayList<StudentsAll> getStudentsByBatch(String curriculum);

    ArrayList<StudentsAll> selectAllAttendanceDate(String selectedDate);


    Attendance findAttendanceByMemberIdAndDate(HashMap<String, String> attendanceMap);

    ArrayList<StudentsAll> ReadOneStudentAttendance(String memberid);

    ArrayList<StudentsAll> SelectMonthAttendance(HashMap<String,String> map);

    ArrayList<TestpaperList> getStudentsGrade(String memberid);
}
