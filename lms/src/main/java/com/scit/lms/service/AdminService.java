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

}
