package com.sbs.exam.demo.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.MemberRepository;
import com.sbs.exam.demo.util.Util;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellPhoneNo,
			String email) {
		// 아이디 중복 체크
		Member oldmember = getMemberByLoginId(loginId);

		if (oldmember != null) {
			return ResultData.from("F-7", Util.f("(%s)(은)는 이미 존재하는 아이디입니다.", loginId));
		}

		// 이름, 이메일 중복 체크
		oldmember = getMemberByNameAndEmail(name, email);

		if (oldmember != null) {
			return ResultData.from("F-8", Util.f("(%s), (%s)(은)는 이미 가입된 회원의 이름과 이메일입니다.", name, email));
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellPhoneNo, email);
		int id = memberRepository.getLastInsertId();

		return ResultData.from("S-1", "회원가입이 완료되었습니다.", "id", id);
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	public boolean isLogined(HttpSession httpSession) {
		boolean isLogined = false;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
		return isLogined;
	}

}
