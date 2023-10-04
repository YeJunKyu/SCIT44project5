package com.scit.lms.service;

import java.util.ArrayList;
import java.util.HashMap;

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
	public ArrayList<StudentsAll> selectAllAttendance(String att_date) {
		return dao.selectAllAttendance(att_date);
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

	@Override
	public void updateCurriculum(StudentsAll studentsAll) {
		dao.updateCurriculum(studentsAll);
	}

	@Override
	public void updatejpClass(StudentsAll studentsAll) {
		dao.updatejpClass(studentsAll);
	}

	@Override
	public void updateitClass(StudentsAll studentsAll) {
		dao.updateitClass(studentsAll);
	}

	@Override
	public void updateInformation(StudentsAll studentsAll) {
		dao.updateInformation(studentsAll);
	}

	@Override
	public void insertOneCurriculum(StudentsAll studentsAll) {
		dao.insertOneCurriculum(studentsAll);
	}

	@Override
	public void insertOneitClass(StudentsAll studentsAll) {
		dao.insertOneitClass(studentsAll);
	}

	@Override
	public void insertOnejpClass(StudentsAll studentsAll) {
		dao.insertOnejpClass(studentsAll);
	}

	@Override
	public void insertOneInformation(StudentsAll studentsAll) {
		dao.insertOneInformation(studentsAll);
	}

	@Override
	public Student selectOneStudent(String memberid) {
		return dao.selectOneStudent(memberid);
	}

	@Override
	public JP_category selectOnejpCategory(String memberid) {
		return dao.selectOnejpCategory(memberid);
	}

	@Override
	public IT_category selectOneitCategory(String memberid) {
		return dao.selectOneitCategory(memberid);
	}

	@Override
	public StudentInfo selectOneStudentInfo(String memberid) {
		return dao.selectOneStudentInfo(memberid);
	}

	@Override
	public ArrayList<StudentsAll> getStudentsByBatch(String curriculum) {
		return dao.getStudentsByBatch(curriculum);
	}

	@Override
	public ArrayList<StudentsAll> selectAllAttendanceDate(String selectedDate) {
		return dao.selectAllAttendanceDate(selectedDate);
	}

	@Override
	public Attendance findAttendanceByMemberIdAndDate(String memberid, String att_date) {
		HashMap<String ,String> attendanceMap = new HashMap<>();
		attendanceMap.put("memberid",memberid);
		attendanceMap.put("att_date",att_date);

		return dao.findAttendanceByMemberIdAndDate(attendanceMap);
	}

	@Override
	public ArrayList<StudentsAll> ReadOneStudentAttendance(String memberid) {
		return dao.ReadOneStudentAttendance(memberid);
	}

	@Override
	public ArrayList<StudentsAll> SelectMonthAttendance(String selectedDate, String memberid) {
		HashMap<String,String> map = new HashMap<>();
		map.put("selectedDate",selectedDate);
		map.put("memberid",memberid);

		return dao.SelectMonthAttendance(map);
	}

	@Override
	public ArrayList<TestpaperList> getStudentsGrade(String memberid) {
		return dao.getStudentsGrade(memberid);
	}

	@Override
	public ArrayList<GradeAll> SelectGrade() {
		return dao.SelectGrade();
	}


}
