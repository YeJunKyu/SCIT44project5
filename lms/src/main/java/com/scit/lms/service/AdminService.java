package com.scit.lms.service;

import com.scit.lms.domain.*;

import java.util.ArrayList;


public interface AdminService {


    ArrayList<Member> selectAll();



    void update(Member member);

    ArrayList<StudentsAll> selectAllStudents();

    ArrayList<StudentsAll> selectOnlyStudent();

    void insertCurriculum(ArrayList<Student> student);

    ArrayList<StudentsAll> selectOnlyStudentClass();

    void insertClass(ArrayList<StudentClasses> studentsAllClasses);

    ArrayList<Member> selectOnlyStudentInformation();

    void insertInformation(ArrayList<StudentInfo> studentAllInfos);

    ArrayList<PrimaryRatio> AllRatios();

    int InsertBigRatio(PrimaryRatio primaryRatio);

    PrimaryRatio selectOneCategory(String categoryname);
}
