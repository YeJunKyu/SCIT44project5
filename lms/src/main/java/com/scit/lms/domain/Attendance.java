package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    int attnum;
    String memberid;
    String att_date;
    String att_type;
    String att_comment;
    String att_permission;
}
