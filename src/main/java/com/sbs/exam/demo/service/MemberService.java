package com.sbs.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.MemberRepository;
import com.sbs.exam.demo.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public int join(String loginId, String loginPw, String name, String nickname, String cellPhoneNo, String email) {
		memberRepository.join(loginId, loginPw, name, nickname, cellPhoneNo, email);
		int id = memberRepository.getLastInsertId();
		return id;
	}

	public Member getFoundMember(int id) {
		return memberRepository.getFoundMember(id);
	}

	public boolean isLoginIdDup(String loginId) {
		return memberRepository.isLoginIdDup(loginId);
	}

}
