package com.scit.lms.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    String memberid;
    String memberpw;
    String membername;
    String birthday;
    String phone;
    String gender;
    String address;
    String note;
    String role;
    String available;
}
