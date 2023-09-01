package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClasses {
    String memberid;
    String jpclassname;
    String jpsubject;
    String itclassname;
    String itsubject;
}
