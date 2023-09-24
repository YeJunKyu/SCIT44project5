package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestUpdateRequest {
    private int testid;
    private String testname;
    private String totalpoints;
    private String testdate;
    private String testlimit;
    private String requestData;
}
