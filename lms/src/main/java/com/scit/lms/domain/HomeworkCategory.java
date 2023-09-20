package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkCategory {
    String memberid;
    String curriculum;
    String jpclassname;
    String jpsubject;
    String itclassname;
    String itsubject;
}
