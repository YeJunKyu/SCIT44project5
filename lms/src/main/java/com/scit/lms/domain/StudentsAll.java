package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentsAll{
    private Member member;
    private Student student;
    private IT_category itCategory;
    private JP_category jpCategory;
    private StudentInfo studentInfo;
}
