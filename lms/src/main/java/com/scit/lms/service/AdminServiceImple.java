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
	public ArrayList<StudentsAll> selectAllAttendance() {
		return dao.selectAllAttendance();
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

	@Override
	public void insertBigRatio(PrimaryRatio primaryRatio) {
		dao.insertBigRatio(primaryRatio);
	}

	@Override
	public PrimaryRatio selectOneCategory(String categoryname) {
		return dao.selectOneCategory(categoryname);
	}

	@Override
	public ArrayList<PrimaryRatio>  ReadBigRatio() {
		return dao.ReadBigRatio();
	}

	@Override
	public void InsertMiddleRatio(PrimaryRatio primaryRatio) {
		dao.InsertMiddleRatio(primaryRatio);
	}

	@Override
	public ArrayList<PrimaryRatio> ReadMiddleRatio() {
		return dao.ReadMiddleRatio();
	}

	@Override
	public ArrayList<PrimaryRatio> ReadTestBigList() {
		return dao.ReadTestBigList();
	}

	@Override
	public ArrayList<PrimaryRatio> ReadTestMiddleList() {
		return dao.ReadTestMiddleList();
	}

	@Override
	public ArrayList<PrimaryRatio> ReadTestSmallList() {
		return dao.ReadTestSmallList();
	}

	@Override
	public void updateTestList(PrimaryRatio primaryRatio) {
		dao.updateTestList(primaryRatio);
	}

	@Override
	public void InsertStudentAttendance(Attendance attendances) {
		dao.InsertStudentAttendance(attendances);
	}

	@Override
	public ArrayList<StudentsAll> ReadStudentAttendance() {
		return dao.ReadStudentAttendance();
	}

	@Override
	public ArrayList<StudentsAll> SelectDateAttendance(String selectedDate) {
		return dao.SelectDateAttendance(selectedDate);
	}

	@Override
	public void UpdateStudentAttendance(Attendance attendance) {
		dao.UpdateStudentAttendance(attendance);
	}

	@Override
	public StudentsAll ReadOneStudent(String memberid) {
		return dao.ReadOneStudent(memberid);
	}


}
