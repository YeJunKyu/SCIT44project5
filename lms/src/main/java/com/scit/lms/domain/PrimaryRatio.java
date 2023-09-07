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

}
