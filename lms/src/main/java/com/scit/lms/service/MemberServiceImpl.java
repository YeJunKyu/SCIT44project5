package com.scit.lms.service;

import com.scit.lms.dao.MemberDAO;
import com.scit.lms.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO dao;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public int join(Member member){
		String pw = passwordEncoder.encode(member.getMemberpw());

		log.debug("암호화 전 : {}", member.getMemberpw());
		log.debug("암호화 후 : {}", pw);

		member.setMemberpw(pw);

		int n = dao.join(member);

		return n;
	}

	@Override
	public boolean idcheck(String searchid) {
		return dao.selectOne(searchid) == null;
	}

	//회원정보 불러오기
	@Override
	public Member memberInfor(String userid){
		Member member = dao.memberInfor(userid);

//		log.debug("&&&&&&&&&&&&{}",member.getGender());
//		if(member.getGender() == "male"){
//			member.setGender("남성");
//		}else {
//			member.setGender("여성");
//		}
		return member;
	}

	//회원정보 수정
	@Override
	public int memberUpdate(Member member){
//		String pw = "";
//		if(member.getPassword() != "") {
//			pw = passwordEncoder.encode(member.getMemberpw());
//			log.debug("!!!!비밀번호 확인{}", pw);
//		}
//
//
//		log.debug("암호화 전 : {}", member.getMemberpw());
//		log.debug("암호화 후 : {}", pw);
//
//		member.setMemberpw(pw);

		int n = dao.memberUpdate(member);

		return n;
	}

	@Override
	public int memberphoto(Member member){
		int n = dao.memberphoto(member);
        return n;
    }


//비밀번호확인
	@Override
	public boolean pwcheck(Member member){
		String inputpw = member.getMemberpw();

		log.debug("입력비번 : {}", inputpw);
		String originpw = dao.pwcheck(member);
		log.debug("원래비번 : {}", originpw);

		boolean matches = passwordEncoder.matches(inputpw, originpw);
		log.debug("매치결과 : {}", matches);

		return matches;
	}


	@Override
	public int deleteMember(Member member){
		log.debug("삭제할회원이름:{}", member.getMembername());
		log.debug("삭제할회원아이디:{}", member.getMemberid());

		int n = dao.deleteMember(member);

		return n;
	}

	@Override
	public int changePW(Member member){

		String changePW = passwordEncoder.encode(member.getMemberpw());
		log.debug("암호전:{}", member.getMemberpw());
		log.debug("암호후:{}", changePW);

		member.setMemberpw(changePW);
		int n = dao.changePW(member);
		return n;
	}

	@Override
	public int checkid(Member member){

		int n = dao.checkid(member);

		return n;
	}
}
