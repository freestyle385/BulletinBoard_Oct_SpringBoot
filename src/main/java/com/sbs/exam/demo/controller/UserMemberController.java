package com.sbs.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.util.Util;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.Member;

@Controller
public class UserMemberController {
	@Autowired // 등록된 컴포넌트를 자동으로 연결 해줌
	private MemberService memberService;
	
	// 액션 메소드 시작
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellPhoneNo, String email) {
		
		if (Util.isParamEmpty(loginId)) {
			return "아이디를 입력해주세요.";
		}
		if (Util.isParamEmpty(loginPw)) {
			return "비밀번호를 입력해주세요.";
		}
		if (Util.isParamEmpty(name)) {
			return "이름을 입력해주세요.";
		}
		if (Util.isParamEmpty(nickname)) {
			return "닉네임을 입력해주세요.";
		}
		if (Util.isParamEmpty(cellPhoneNo)) {
			return "연락처를 입력해주세요.";
		}
		if (Util.isParamEmpty(email)) {
			return "이메일을 입력해주세요.";
		}
		
		int id = memberService.join(loginId, loginPw, name, nickname, cellPhoneNo, email);
		
		if (id == -1) {
			return "이미 존재하는 아이디입니다.";
		}
		
		if (id == -2) {
			return "이미 가입된 회원의 이름과 이메일입니다.";
		}
		
		Member foundMember = memberService.getMemberById(id);
		
		return foundMember;
	}

	@RequestMapping("/usr/member/getMemberInfo")
	@ResponseBody
	public Object getMemberInfo(int id) {
		Member foundMember = memberService.getMemberById(id);

		if (foundMember == null) {
			return id + "번 회원은 존재하지 않습니다.";
		}

		return foundMember;
	}
	// 액션 메소드 끝
}