package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrimaryRatio {
    int category_id;
    int parent_id;
    String categoryname;
    double ratio;
    String divname;

    //수정용 다른테이블컬럼
    String testname;
    
    //수정을위해 변환
    public String assignTestNameToCategoryName(String testname) {
        this.categoryname = this.testname;
        return testname;
    }
}
