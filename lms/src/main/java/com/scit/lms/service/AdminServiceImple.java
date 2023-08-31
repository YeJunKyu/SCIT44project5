package com.scit.lms.service;

import java.util.ArrayList;

import com.scit.lms.dao.AdminDAO;
import com.scit.lms.domain.Member;
import com.scit.lms.domain.StudentsAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Service
public class AdminServiceImple implements AdminService {

	@Autowired
	AdminDAO dao;


	@Override
	public ArrayList<Member> selectAll() {
		return dao.selectAll();
	}

	@Override
	public void update(Member member) {
		dao.update(member);
	}

	@Override
	public ArrayList<StudentsAll> selectAllStudents() {
		return dao.selectAllStudents();
	}


}
