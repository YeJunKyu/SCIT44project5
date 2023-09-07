package com.scit.lms.service;

import java.util.ArrayList;

import com.scit.lms.dao.AdminDAO;
import com.scit.lms.domain.*;
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

	@Override
	public ArrayList<StudentsAll> selectOnlyStudent() {
		return dao.selectOnlyStudent();
	}

	@Override
	public void insertCurriculum(ArrayList<Student> student) {
		dao.insertCurriculum(student);
	}

	@Override
	public ArrayList<StudentsAll> selectOnlyStudentClass() {
		return dao.selectOnlyStudentClass();
	}

	@Override
	public void insertClass(ArrayList<StudentClasses> studentsAllClasses) {
		dao.insertClass(studentsAllClasses);
	}

	@Override
	public ArrayList<Member> selectOnlyStudentInformation() {
		return dao.selectOnlyStudentInformation();
	}

	@Override
	public void insertInformation(ArrayList<StudentInfo> studentAllInfos) {
		dao.insertInformation(studentAllInfos);
	}

	@Override
	public ArrayList<PrimaryRatio> AllRatios() {
		return dao.AllRatios();
	}


}
