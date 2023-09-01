package com.scit.lms.service;

import com.scit.lms.domain.Member;
import com.scit.lms.domain.Student;
import com.scit.lms.domain.StudentClasses;
import com.scit.lms.domain.StudentsAll;

import java.util.ArrayList;


public interface AdminService {


    ArrayList<Member> selectAll();



    void update(Member member);

    ArrayList<StudentsAll> selectAllStudents();

    ArrayList<Member> selectOnlyStudent();

    void insertCurriculum(ArrayList<Student> student);

    ArrayList<Member> selectOnlyStudentClass();

    void insertClass(ArrayList<StudentClasses> studentsAllClasses);
}
