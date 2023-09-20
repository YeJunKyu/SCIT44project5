package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkCategory {
    public String memberid;
    public String curriculum;
    public String jpclassname;
    public String itclassname;
}
