package com.scit.lms.controller;

import com.scit.lms.domain.Member;
import com.scit.lms.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {
    @Autowired
    MemberService service;
    //회원가입폼으로이동
    @GetMapping("join")
    public String join(){
        return "join";
    }
    //회원가입 데이터 전달
    @PostMapping("join")
    @ResponseBody
    public Map<String, Object> join(Member member){
        Map<String, Object> resultMap = new HashMap<>();
        System.out.println("member : " + member);


        int n = service.join(member);

        if(n == 1 ){
            resultMap.put("msg","가입 완료");
        } else {
            resultMap.put("msg","가입 실패");
        }

        return resultMap;
    }
    @GetMapping("login")
    public String main(){
        return "main";
    }
}
