package com.scit.lms.service;

import com.scit.lms.domain.Member;

public interface MemberService {

    public int join(Member member);

    boolean idcheck(String searchid);
}
