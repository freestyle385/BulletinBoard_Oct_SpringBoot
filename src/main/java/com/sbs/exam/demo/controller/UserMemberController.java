package com.sbs.exam.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.util.Util;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;

@Controller
public class UserMemberController {
	@Autowired // 등록된 컴포넌트를 자동으로 연결 해줌
	private MemberService memberService;

	// 액션 메소드 시작
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellPhoneNo,
			String email) {

		if (Util.isParamEmpty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요.");
		}
		if (Util.isParamEmpty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요.");
		}
		if (Util.isParamEmpty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요.");
		}
		if (Util.isParamEmpty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요.");
		}
		if (Util.isParamEmpty(cellPhoneNo)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요.");
		}
		if (Util.isParamEmpty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요.");
		}
		// S-1
		// 회원가입이 완료되었습니다.

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellPhoneNo, email);

		if (joinRd.isFail()) {
			return (ResultData) joinRd;
		}

		Member foundMember = memberService.getMemberById(joinRd.getData1());

		return ResultData.newData(joinRd, foundMember);
	}

	@RequestMapping("/usr/member/getMemberInfo")
	@ResponseBody
	public ResultData<Member> getMemberInfo(int id) {
		Member foundMember = memberService.getMemberById(id);

		if (foundMember == null) {
			return ResultData.from("F-1", Util.f("%d번 회원은 존재하지 않습니다.", id));
		}

		return ResultData.from("S-1", Util.f("%d번 회원이 조회되었습니다.", id), foundMember);
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession httpSession, String loginId, String loginPw) {
		
		if (memberService.isLogined(httpSession) == true) {
			return ResultData.from("F-A", "이미 로그인 상태입니다.");
		}

		if (Util.isParamEmpty(loginId)) {
			return ResultData.from("F-1", "loginId을(를) 입력해주세요.");
		}
		if (Util.isParamEmpty(loginPw)) {
			return ResultData.from("F-2", "loginPw을(를) 입력해주세요.");
		}

		Member foundMember = memberService.getMemberByLoginId(loginId);

		if (foundMember == null) {
			return ResultData.from("F-3", "존재하지 않는 아이디 입니다.");
		}

		if (foundMember.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}

		httpSession.setAttribute("loginedMemberId", foundMember.getId());

		return ResultData.from("S-1", Util.f("%s님 환영합니다.", foundMember.getNickname()));
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession httpSession) {

		if (memberService.isLogined(httpSession) == false) {
			return ResultData.from("F-A", "로그인 상태가 아닙니다.");
		}

		httpSession.invalidate();

		return ResultData.from("S-1", Util.f("정상적으로 로그아웃 되었습니다."));
	}
	// 액션 메소드 끝
	
}