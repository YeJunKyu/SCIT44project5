package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    int noticenum;
    String memberid;
    String noticetitle;
    String noticecontents;
    String noticedate;
    int viewauthority;
    int hits;
    String originalfile;
    String savedfile;
}
