package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    String eventid;
    String title;
    String note;
    String startdate;
    String enddate;
    boolean allday;
    String color;
    String textcolor;
    String memberid;
    String categories;
}