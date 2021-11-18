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

	public Member joinMember(String loginId, String loginPw, String name, String nickname, String cellPhoneNo,
			String email) {

		memberRepository.join(loginId, loginPw, name, nickname, cellPhoneNo, email);
		int id = memberRepository.getLastInsertId();

		return getMemberById(id);
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

	public boolean isLoginIdAlreadyExists(String loginId) {
		boolean isAlreadyExists = false;
		Member oldmember = getMemberByLoginId(loginId);
		
		if (oldmember != null) {
			isAlreadyExists = true;
		}
		return isAlreadyExists;
	}

	public boolean isNameAndEmailAlreadyExists(String name, String email) {
		boolean isNameAndEmailAlreadyExists = false;
		Member oldmember = getMemberByNameAndEmail(name, email);
		
		if (oldmember != null) {
			isNameAndEmailAlreadyExists = true;
		}
		return isNameAndEmailAlreadyExists;
	}

}
