package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfo {
    String memberid;
    String certi_jpname;
    String certi_name;
    String company;
    String note;
    String experience;
    String scitgraduation;
    String originalphoto;
    String savedphoto;
}
