package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkStudent {
    public String memberid;
    public String school;
    public String major;
    public int graduation;
    public String curriculum;
    public String jpclassname;
    public String jpsubject;
    public String itclassname;
    public String itsubject;
    public int hw_num;
    public int hw_sub_num;
    public String hw_sub_contents;
    public String hw_sub_inputdate;
    public String hw_sub_done;
    public String hw_sub_comment;
    public int hw_sub_score;
    public String originalfile;
    public String savedfile;
}
