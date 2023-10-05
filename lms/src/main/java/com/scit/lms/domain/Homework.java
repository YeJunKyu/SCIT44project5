package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Homework {
    int hw_num;
    String hw_title;
    String hw_contents;
    String hw_curriculum;
    String hw_subject;
    String hw_class;
    String hw_inputdate;
    String hw_start;
    String hw_end;
    String originalfile;
    String savedfile;
    String memberid;
}
