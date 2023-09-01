package com.scit.lms.dao;

import java.util.ArrayList;

import com.scit.lms.domain.Member;
import com.scit.lms.domain.Student;
import com.scit.lms.domain.StudentClasses;
import com.scit.lms.domain.StudentsAll;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdminDAO {


    ArrayList<Member> selectAll();



    void update(Member member);

    ArrayList<StudentsAll> selectAllStudents();

    ArrayList<Member> selectOnlyStudent();

    void insertCurriculum(ArrayList<Student> students);

    ArrayList<Member> selectOnlyStudentClass();

    void insertClass(ArrayList<StudentClasses> studentsAllClasses);
}
