package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    int optionid;
    long qid;
    String value;
    String content;
    String checked;
}
