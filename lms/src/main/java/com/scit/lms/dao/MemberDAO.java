package com.scit.lms.dao;

import com.scit.lms.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
    public int join(Member member);

    public Member selectOne(String searchid);
}
