package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeAll {
    Member member;
    Student student;
    StudentsAll studentsAll;
    PrimaryRatio primaryRatio;
    Test test;
    TestpaperList testpaperList;
    AllOfTest allOfTest;

}
