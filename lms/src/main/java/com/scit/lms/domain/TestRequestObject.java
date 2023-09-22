package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestRequestObject {
    // 특정 목적 없이 단순 정보 전달을 위해 사용하는 클래스
    int testid;
    String testname;
    String testdate;
    String testlimit;
    String totalpoints;
    Question[] questionDataArray; // 문제와 보기 및 답안이 담긴 배열

}
