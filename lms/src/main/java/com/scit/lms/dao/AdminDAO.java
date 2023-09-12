package com.scit.lms.dao;

import java.util.ArrayList;

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

    ArrayList<StudentsAll> selectAllAttendance();
}
