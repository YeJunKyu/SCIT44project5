package com.scit.lms.service;

import com.scit.lms.domain.Member;
import com.scit.lms.domain.StudentsAll;

import java.util.ArrayList;


public interface AdminService {


    ArrayList<Member> selectAll();



    void update(Member member);

    ArrayList<StudentsAll> selectAllStudents();
}
