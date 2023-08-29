package com.scit.lms.controller;

import java.util.ArrayList;

import com.scit.lms.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	public String update(Member member){
		log.debug("인증수정1:{}",member);
		int n = service.update(member);
		log.debug("인증수정2:{}",member);
		return "redirect:/admin/authentication";
	}
}
