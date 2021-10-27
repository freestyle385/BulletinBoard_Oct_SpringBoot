package com.sbs.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.vo.Member;

@Controller
public class UserMemberController {
	@Autowired // 등록된 컴포넌트를 자동으로 연결 해줌
	private MemberService memberService;

	// 액션 메소드 시작
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Member doJoin(String loginId, String loginPw, String name, String nickname, String cellPhoneNo, String email) {
		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellPhoneNo, email);
		Member foundMember = memberService.getFoundMember(id);
		
		return foundMember;
	}

	// 액션 메소드 끝
}