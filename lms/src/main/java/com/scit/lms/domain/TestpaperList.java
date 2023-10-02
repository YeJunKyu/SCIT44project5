package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestpaperList {
    int asnum;  //답안지 고유번호(시퀀스, PK)
    int testid; //시험 고유번호(FK)
    String memberid; //제출자 id
    String membername; //제출자 이름
    int totalscore;  //시험 점수
    String checked; //시험 체크 여부
}
