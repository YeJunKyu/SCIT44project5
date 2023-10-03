package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllOfTest {
    String memberid;
    Test test;
    TestAnswer testAnswer;
    TestpaperList testpaperList;
    TestRequestObject testRequestObject;
    Question question;
    Option option;

}
