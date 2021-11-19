package com.sbs.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.util.Util;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UserMemberController {
	@Autowired // 등록된 컴포넌트를 자동으로 연결 해줌
	private MemberService memberService;

	// 액션 메소드 시작
	@RequestMapping("/usr/member/join")
	public String showJoin() {

		return "/usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellPhoneNo,
			String email) {

		if (Util.isParamEmpty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요.");
		}
		if (Util.isParamEmpty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요.");
		}
		if (Util.isParamEmpty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요.");
		}
		if (Util.isParamEmpty(nickname)) {
			return Util.jsHistoryBack("닉네임을 입력해주세요.");
		}
		if (Util.isParamEmpty(cellPhoneNo)) {
			return Util.jsHistoryBack("전화번호를 입력해주세요.");
		}
		if (Util.isParamEmpty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요.");
		}

		// 아이디 중복 체크
		if (memberService.isLoginIdAlreadyExists(loginId)) {
			return Util.jsHistoryBack(Util.f("(%s)(은)는 이미 존재하는 아이디입니다.", loginId));
		}

//		// 이름, 이메일 중복 체크
//		if (memberService.isNameAndEmailAlreadyExists(name, email)) {
//			return Util.jsHistoryBack(Util.f("(%s), (%s)(은)는 이미 가입된 회원의 이름과 이메일입니다.", name, email));
//		}

		Member foundMember = memberService.joinMember(loginId, loginPw, name, nickname, cellPhoneNo, email);

		return Util.jsReplace(Util.f("%s님의 회원가입이 완료되었습니다.", foundMember.getNickname()), "/");
	}

//	@RequestMapping("/usr/member/getMemberInfo")
//	@ResponseBody
//	public ResultData<Member> getMemberInfo(int id) {
//		Member foundMember = memberService.getMemberById(id);
//
//		if (foundMember == null) {
//			return ResultData.from("F-1", Util.f("%d번 회원은 존재하지 않습니다.", id));
//		}
//
//		return ResultData.from("S-1", Util.f("%d번 회원이 조회되었습니다.", id), "member", foundMember);
//	}

	@RequestMapping("/usr/member/login")
	public String showLogin(HttpServletRequest req) {

		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw) {
		Rq rq = (Rq) req.getAttribute("rq");

		if (Util.isParamEmpty(loginId)) {
			return Util.jsHistoryBack("loginId을(를) 입력해주세요.");
		}
		if (Util.isParamEmpty(loginPw)) {
			return Util.jsHistoryBack("loginPw을(를) 입력해주세요.");
		}

		Member foundMember = memberService.getMemberByLoginId(loginId);

		if (foundMember == null) {
			return Util.jsHistoryBack("존재하지 않는 아이디 입니다.");
		}

		if (foundMember.getLoginPw().equals(loginPw) == false) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}

		rq.login(foundMember);

		return Util.jsReplace(Util.f("%s님 환영합니다.", foundMember.getNickname()), "/");
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		Rq rq = (Rq) req.getAttribute("rq");

		rq.logout();

		return Util.jsReplace("정상적으로 로그아웃 되었습니다.", "/");
	}
	// 액션 메소드 끝

}