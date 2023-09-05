package com.scit.lms.service;

import com.scit.lms.domain.Member;

public interface MemberService {

    public int join(Member member);

    boolean idcheck(String searchid);

    //회원정보 불러오기
    public Member memberInfor(String userid);

    //회원정보 수정
    public int memberUpdate(Member member);

    //사진 업데이트
    public int memberphoto(Member member);
}
