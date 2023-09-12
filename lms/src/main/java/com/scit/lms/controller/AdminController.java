package com.scit.lms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.scit.lms.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import com.scit.lms.service.AdminService;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	AdminService service;


	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	//게시판 목록의 페이지당 글 수
	@Value("${user.board.page}")
	int countPerPage;
	
	//게시판 목록의 페이지 이동 링크 수
	@Value("${user.board.group}")
	int pagePerGroup;

	@GetMapping("authentication")
	public String authentication(Model model){
		log.debug("인증멤버1:{}","확인");
		ArrayList<Member> list = service.selectAll();

		log.debug("인증멤버2:{}",list);
		model.addAttribute("list",list);

		return "adminView/authentication";
	}

	@PostMapping("update")
	public String update(@RequestParam Map<String, String> formData,
						 @AuthenticationPrincipal UserDetails userDetails){

		// 로그인한 사용자의 memberid 추출
		String loggedInMemberId = userDetails.getUsername();

		// 관리자 권한 확인
		if (!userDetails.getAuthorities().stream().anyMatch(auth -> "ROLE_admin".equals(auth.getAuthority()))) {
			return "adminView/warning";  // 관리자 권한이 아닐 때 경고 페이지로 이동
		}




		ArrayList<Member> membersToUpdate = new ArrayList<>();

		log.debug("수정1:{}",membersToUpdate);
		for (Map.Entry<String, String> entry : formData.entrySet()) {
			if (entry.getKey().startsWith("enabled")) {


				String memberId = entry.getKey().replace("enabled", "");  // "enabled"를 제거하고 memberId 추출

				// 로그인한 사용자의 memberid와 일치하는 회원의 수정을 막는 조건
				if (loggedInMemberId.equals(memberId)) {
					continue;  // 현재 회원의 처리를 건너뜁니다.
				}
				Member member = new Member();
				member.setMemberid(memberId);
				member.setEnabled(Boolean.parseBoolean(entry.getValue()));
				member.setRolename(formData.get("rolename" + memberId));

				log.debug("수정멤버:{}", member);
				membersToUpdate.add(member);
			}
		}
		log.debug("수정3:{}",membersToUpdate);
		// 각 멤버를 순회하며 업데이트
		for (Member member : membersToUpdate) {
			service.update(member);
		}


		log.debug("수정4:{}",membersToUpdate);



		return "redirect:/admin/authentication";
	}
	
	//학생관리 학생정보조회
	@GetMapping("studentManager")
	public String studentManager(Model model,
								 StudentsAll studentsAll){
		log.debug("컨트롤러 확인:{}","확인");


		ArrayList<StudentsAll> studentslist = service.selectAllStudents();
		log.debug("학생확인:{}",studentslist);

		model.addAttribute("students",studentslist);
		log.debug("학생확인2:{}",studentslist);

		return "adminView/studentManager";
	}

	
	//학생관리 : 과정등록폼
	@GetMapping("insertCurriculum")
	public String insertStudentA(Model model){
		log.debug("컨트롤러확인:{}","okay");
		ArrayList<StudentsAll> studentsOnly = service.selectOnlyStudent();
		log.debug("과정폼1:{}",studentsOnly);
		model.addAttribute("student",studentsOnly);
		return "adminView/insertCurriculum";
	}
	
	//학생관리 : 과정등록 다중인설트
	@PostMapping("insertCurriculum")
	public String insertCurriculum(@RequestParam("memberid") ArrayList<String> memberids,
								   @RequestParam String curriculum){
		log.debug("컨트롤러확인:{}","okay");
		ArrayList<Student> students = new ArrayList<>();

		for (String memberid : memberids) {
			log.debug("Member ID: {}", memberid);

			Student student = new Student();
			student.setMemberid(memberid);
			student.setCurriculum(curriculum);
			students.add(student);
		}

		service.insertCurriculum(students);
		log.debug("과정등록1: {}", students);
		return "redirect:/admin/insertCurriculum";
	}

	//학생관리 : 분반 등록폼
	@GetMapping("insertClass")
	public String insertClass(Model model){
		log.debug("컨트롤러확인:{}","okay");
		ArrayList<StudentsAll> studentsOnly = service.selectOnlyStudentClass();
		log.debug("분반폼1:{}",studentsOnly);
		model.addAttribute("student",studentsOnly);
		return "adminView/insertClass";
	}

	//학생분반등록 다중인설트
	@PostMapping("insertClass")
	public String insertClass(@RequestParam("memberid") ArrayList<String> memberids,
							  String jpclassname,
							  String jpsubject,
							  String itclassname,
							  String itsubject){
		ArrayList<StudentClasses> studentsAllClasses = new ArrayList<>();
		log.debug("컨트롤러확인:{}",studentsAllClasses);

		for (String memberid : memberids) {
			StudentClasses studentClass = new StudentClasses();
			studentClass.setMemberid(memberid);
			studentClass.setJpclassname(jpclassname);
			studentClass.setJpsubject(jpsubject);
			studentClass.setItclassname(itclassname);
			studentClass.setItsubject(itsubject);
			studentsAllClasses.add(studentClass);
		}
		log.debug("컨트롤러 확인2:{}",studentsAllClasses);
		service.insertClass(studentsAllClasses);
		log.debug("분반등록:{}",studentsAllClasses);
		return "redirect:/admin/insertClass";
	}

	//학생관리 : 기타정보 등록폼
	@GetMapping("insertInformation")
	public String insertInformation(Model model){
		log.debug("컨트롤러확인:{}","okay");
		ArrayList<Member> studentsOnly = service.selectOnlyStudentInformation();
		log.debug("기타정보폼1:{}",studentsOnly);
		model.addAttribute("student",studentsOnly);
		return "adminView/insertInformation";
	}

	//학생 관리 : 기타정보 등록
	@PostMapping("insertInformation")
	public String insertInformation(@RequestParam("memberid") ArrayList<String> memberids,
									@RequestParam String certi_jpname,
									@RequestParam String certi_name,
									@RequestParam String scitgraduation){
		ArrayList<StudentInfo> studentAllInfos = new ArrayList<>();
		log.debug("컨트롤러확인:{}",studentAllInfos);
		for (String memberid : memberids) {
			StudentInfo studentInfo = new StudentInfo();
			studentInfo.setMemberid(memberid);
			studentInfo.setCerti_jpname(certi_jpname);
			studentInfo.setCerti_name(certi_name);
			studentInfo.setScitgraduation(scitgraduation);
			studentAllInfos.add(studentInfo);
		}
		log.debug("컨트롤러확인2:{}",studentAllInfos);
		service.insertInformation(studentAllInfos);
		log.debug("기타정보등록확인:{}",studentAllInfos);
		return "redirect:/admin/insertInformation";
	}

	//시험비중등록폼
	@GetMapping("InsertBigRatio")
	public String InsertRation(Model model){




		return "adminView/InsertBigRatio";
	}

	//시험비중등록대분류

	@PostMapping("InsertBigRatio")
	public String InsertBigRatio(@RequestParam("categoryname[]") String[] categoryname,
								 @RequestParam("ratio[]") Double[] ratio,
								 String divname
			)
	{	log.debug("컨트롤러확인:{}",categoryname.length);
		ArrayList<PrimaryRatio> primaryRatios = new ArrayList<>();
		for(int i = 0;i<categoryname.length;i++){
			if(!categoryname[i].isEmpty() && ratio[i] != 0.00) {
				PrimaryRatio primaryRatio = new PrimaryRatio();
				primaryRatio.setCategoryname(categoryname[i]);
				primaryRatio.setRatio(ratio[i]);
				primaryRatio.setDivname(divname);
				primaryRatios.add(primaryRatio);
			}
		}
		log.debug("비중배열확인:{}",primaryRatios);
		for (PrimaryRatio primaryRatio : primaryRatios) {
			service.insertBigRatio(primaryRatio);
		}
		log.debug("비중등록확인:{}",primaryRatios);
		return "redirect:/admin/InsertBigRatio";
	}
	
	//대분류 등록후 중분류 이동
	@GetMapping("InsertMiddleRatio")
	public String InsertMiddleRatio(Model model){
			log.debug("컨트롤러 확인:{}","okay");
			ArrayList<PrimaryRatio>  primaryRatio = service.ReadBigRatio();
			log.debug("대분류데이터:{}",primaryRatio);
			model.addAttribute("pri",primaryRatio);

			return "adminView/InsertMiddleRatio";
		}

	//시험비중등록 중분류
	@PostMapping("InsertMiddleRatio")
	public String InsertMiddleRatio(int parent_id,
			@RequestParam("categoryname[]") String[] categoryname,
									@RequestParam("ratio[]") Double[] ratio,
									String divname){
		log.debug("컨트롤러 확인:{},{}",parent_id,categoryname);
		ArrayList<PrimaryRatio> primaryRatios = new ArrayList<>();
		for(int i = 0;i<categoryname.length;i++){
			if(!categoryname[i].isEmpty() && ratio[i] != 0.00) {
				PrimaryRatio primaryRatio = new PrimaryRatio();
				primaryRatio.setParent_id(parent_id);
				primaryRatio.setCategoryname(categoryname[i]);
				primaryRatio.setRatio(ratio[i]);
				primaryRatio.setDivname(divname);
				primaryRatios.add(primaryRatio);
			}
		}

		log.debug("비중배열확인:{}",primaryRatios);
		for (PrimaryRatio primaryRatio : primaryRatios) {
			service.InsertMiddleRatio(primaryRatio);
		}
		log.debug("비중등록확인:{}",primaryRatios);

		return "redirect:/admin/InsertMiddleRatio";
	}

	//시험등록소분류 폼
	@GetMapping("InsertSmallRatio")
	public String InsertSmallRatio(Model model){
		log.debug("컨트롤러 확인:{}","okay");
		ArrayList<PrimaryRatio>  primaryRatio = service.ReadMiddleRatio();
		log.debug("중분류데이터:{}",primaryRatio);
		model.addAttribute("pri",primaryRatio);
		
		return "adminView/InsertSmallRatio";
	}
	
	//시험등록소분류
	@PostMapping("InsertSmallRatio")
	public String InsertSmallRatio(int parent_id,
									@RequestParam("categoryname[]") String[] categoryname,
									@RequestParam("ratio[]") Double[] ratio,
									String divname){
		log.debug("컨트롤러 확인:{},{}",parent_id,categoryname);
		ArrayList<PrimaryRatio> primaryRatios = new ArrayList<>();
		for(int i = 0;i<categoryname.length;i++){
			if(!categoryname[i].isEmpty() && ratio[i] != 0.00) {
				PrimaryRatio primaryRatio = new PrimaryRatio();
				primaryRatio.setParent_id(parent_id);
				primaryRatio.setCategoryname(categoryname[i]);
				primaryRatio.setRatio(ratio[i]);
				primaryRatio.setDivname(divname);
				primaryRatios.add(primaryRatio);
			}
		}

		log.debug("비중배열확인:{}",primaryRatios);
		for (PrimaryRatio primaryRatio : primaryRatios) {
			service.InsertMiddleRatio(primaryRatio);
		}
		log.debug("비중등록확인:{}",primaryRatios);

		return "redirect:/admin/InsertSmallRatio";
	}
	
	//시험조회
	@GetMapping("ReadTestList")
	public String ReadTestList(Model model)
	{
		log.debug("시험조회1:{}","확인");
		ArrayList<PrimaryRatio> bigList = service.ReadTestBigList();
		ArrayList<PrimaryRatio> middleList = service.ReadTestMiddleList();
		ArrayList<PrimaryRatio> smallList = service.ReadTestSmallList();
		log.debug("시험조회2:{}",bigList);
		log.debug("시험조회3:{}",middleList);
		log.debug("시험조회4:{}",smallList);
		model.addAttribute("bigList",bigList);
		model.addAttribute("midList",middleList);
		model.addAttribute("smList",smallList);


		return "adminView/ReadTestList";
	}

	//시험종목,비중수정
	@PostMapping("updateTestList")
	public String updateTestList(@RequestParam Map<String, String> formData){
		log.debug("데이터수:{},데이터:{}",formData.size(),formData);
		ArrayList<PrimaryRatio> primaryRatios = new ArrayList<>();

		for (Map.Entry<String, String> entry : formData.entrySet()) {
			if (entry.getKey().startsWith("selectedCheckbox")) {
				int categoryId = Integer.parseInt(entry.getValue());

				String categoryName = formData.get("categoryname" + categoryId);
				Double ratioValue = Double.parseDouble(formData.get("ratio" + categoryId));
				String divName = formData.get("divname" + categoryId);

				if (categoryName != null && ratioValue != null && divName != null) {
					PrimaryRatio primaryRatio = new PrimaryRatio();
					primaryRatio.setCategory_id(categoryId);
					primaryRatio.setCategoryname(categoryName);
					primaryRatio.setRatio(ratioValue);
					primaryRatio.setDivname(divName);
					primaryRatios.add(primaryRatio);
					log.debug("담은데이터확인:{}",primaryRatios);
				}
			}
		}
		log.debug("데이터확인:{}",primaryRatios);
		for (PrimaryRatio primaryRatio : primaryRatios) {
			service.updateTestList(primaryRatio);
		}
		log.debug("수정확인:{}",primaryRatios);
		return "redirect:/admin/ReadTestList";
	}


	//출결 등록 폼
	@GetMapping("InsertStudentAttendance")
	public String InsertStudentAttendance(Model model){
		log.debug("출결멤버1:{}","확인");
		ArrayList<StudentsAll> list = service.selectAllAttendance();
		log.debug("출결멤버2:{}",list);
		log.debug("멤버수:{}",list.size());
		model.addAttribute("list",list);
		return "adminView/InsertStudentAttendance";
	}
	
	//출결 등록
	@PostMapping("InsertStudentAttendance")
	public String InsertStudentAttendance(@RequestParam Map<String, String> allParams){
		log.debug("데이터:{}",allParams);
		ArrayList<Attendance> attendances = new ArrayList<>();

		for (String key : allParams.keySet()) {
			if (key.startsWith("att_type")) {
				String memberId = key.replace("att_type", "");

				Attendance attendance = new Attendance();
				attendance.setMemberid(memberId);
				attendance.setAtt_type(allParams.get(key));
				attendance.setAtt_date(allParams.get("att_date"));

				// permission 처리
				String permissionKey = memberId + "-permission";
				log.debug("key확인:{}",permissionKey);
				if (allParams.containsKey(permissionKey)) {
					attendance.setAtt_permission(allParams.get(permissionKey));
				} else {
					attendance.setAtt_permission(null);  // 혹은 다른 기본값 설정
				}
				log.debug("출결데이터:{}",attendance);
				attendances.add(attendance);
			}
		}
		log.debug("출결확인:{}",attendances);
		for (Attendance attendance : attendances) {
			service.InsertStudentAttendance(attendance); // 실제 등록 로직
		}
		log.debug("출결등록:{}",attendances);



		return "redirect:/admin/InsertStudentAttendance";
	}

	//출결 조회 및 수정폼
	@GetMapping("ReadStudentAttendance")
	public String ReadStudentAttendance(Model model){

		return "adminView/ReadStudentAttendance";
	}
}
