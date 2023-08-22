package com.scit.lms.service;

import com.scit.lms.dao.MemberDAO;
import com.scit.lms.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO dao;

	@Override
	public int join(Member member){
		int n = dao.join(member);

		return n;
	}
}
