package com.scit.lms.controller;

import java.util.ArrayList;
import java.util.Map;

import com.scit.lms.domain.Member;
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



}
