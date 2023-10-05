package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Primary_grade {
    int gradenum;
    String memberid;
    String membername;
    String curriculum;
    int testid;
    int categoryid;
    int parent_id;
    int totalscore;
    String categoryname;
    double ratio;
}
