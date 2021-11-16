package com.sbs.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Getter;

public class Rq {
	// 로그인 여부와 로그인 중인 아이디를 가져오는 클래스
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		
		this.session = req.getSession();
		this.req = req;
		this.resp = resp;
		
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;

	}

	public void printHistorybackJs() {
		
		resp.setContentType("text/html; charset=utf-8");
		print("<script>");
		print("alert('로그인 후 이용해주세요.');");
		print("location.replace('/usr/member/login');");
		print("</script>");
	}

	public void print(String msg) {
		try {
			resp.getWriter().append(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(Member foundMember) {
		session.setAttribute("loginedMemberId", foundMember.getId());
	}

	public void logout() {
		session.invalidate();
		
	}
}
