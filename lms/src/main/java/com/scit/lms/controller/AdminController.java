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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		ArrayList<Member> studentsOnly = service.selectOnlyStudent();
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
		return "redirect:/main";
	}

	//학생관리 : 분반 등록
	@GetMapping("insertClass")
	public String insertClass(Model model){
		log.debug("컨트롤러확인:{}","okay");
		ArrayList<Member> studentsOnly = service.selectOnlyStudentClass();
		log.debug("분반폼1:{}",studentsOnly);
		model.addAttribute("student",studentsOnly);
		return "adminView/insertClass";
	}

	//학생분반등록
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

	//학생관리 : 기타정보 등록
	@GetMapping("insertInformation")
	public String insertInformation(Model model){

		return "adminView/insertInformation";
	}


}
