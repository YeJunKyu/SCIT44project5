package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestRequestObject {
    String testname;
    String testdate;
    String testlimit;
    Question[] questionDataArray;

}
