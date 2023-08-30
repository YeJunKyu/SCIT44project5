package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    int uniq_qnum;
    int testid;
    int qbnum;
    String contents;
    int points;
    String type;
    String originalfile;
    String savedfile;
    String answer;
}
