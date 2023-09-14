package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkSub {
    int hw_sub_num;
    int hw_num;
    String hw_sub_contents;
    String hw_sub_inputdate;
    String hw_sub_done;
    String hw_sub_comment;
    String hw_sub_score;
    String originalfile;
    String savedfile;
    String memberid;
}
