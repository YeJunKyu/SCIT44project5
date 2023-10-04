package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentGrade {
    String memberid;
    String membername;
    String curriculum;
    ArrayList<GradeAll> tests;

}
