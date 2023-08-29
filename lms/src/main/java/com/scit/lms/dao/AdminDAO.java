package com.scit.lms.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.scit.lms.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;


@Mapper
public interface AdminDAO {


    ArrayList<Member> selectAll();



    void update(Member member);
}
