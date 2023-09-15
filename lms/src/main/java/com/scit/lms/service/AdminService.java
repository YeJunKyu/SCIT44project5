package com.scit.lms.service;

import com.scit.lms.domain.*;

import java.util.ArrayList;


public interface AdminService {


    ArrayList<Member> selectAll();

    ArrayList<StudentsAll> selectAllAttendance();

    void update(Member member);

    ArrayList<StudentsAll> selectAllStudents();

    ArrayList<StudentsAll> selectOnlyStudent();

    void insertCurriculum(ArrayList<Student> student);

    ArrayList<StudentsAll> selectOnlyStudentClass();

    void insertClass(ArrayList<StudentClasses> studentsAllClasses);

    ArrayList<Member> selectOnlyStudentInformation();

    void insertInformation(ArrayList<StudentInfo> studentAllInfos);

    ArrayList<PrimaryRatio> AllRatios();

    void insertBigRatio(PrimaryRatio primaryRatio);

    PrimaryRatio selectOneCategory(String categoryname);

    ArrayList<PrimaryRatio>  ReadBigRatio();

    void InsertMiddleRatio(PrimaryRatio primaryRatio);

    ArrayList<PrimaryRatio> ReadMiddleRatio();

    ArrayList<PrimaryRatio> ReadTestBigList();

    ArrayList<PrimaryRatio> ReadTestMiddleList();

    ArrayList<PrimaryRatio> ReadTestSmallList();

    void updateTestList(PrimaryRatio primaryRatio);

    void InsertStudentAttendance(Attendance attendance);

    ArrayList<StudentsAll> ReadStudentAttendance();

    ArrayList<StudentsAll> SelectDateAttendance(String selectedDate);

    void UpdateStudentAttendance(Attendance attendance);

    StudentsAll ReadOneStudent(String memberid);

    void updateCurriculum(StudentsAll studentsAll);

    void updatejpClass(StudentsAll studentsAll);

    void updateitClass(StudentsAll studentsAll);

    void updateInformation(StudentsAll studentsAll);

    void insertOneCurriculum(StudentsAll studentsAll);

    void insertOneitClass(StudentsAll studentsAll);

    void insertOnejpClass(StudentsAll studentsAll);

    void insertOneInformation(StudentsAll studentsAll);

    Student selectOneStudent(String memberid);

    JP_category selectOnejpCategory(String memberid);

    IT_category selectOneitCategory(String memberid);

    StudentInfo selectOneStudentInfo(String memberid);
}
