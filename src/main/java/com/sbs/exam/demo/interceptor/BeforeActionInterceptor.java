package com.sbs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.exam.demo.vo.Rq;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private Rq rq;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		// Rq 객체는 Rq 클래스에서 자동으로 만들어지기 때문에 별도 생성 필요 없음.
		// 싱글톤 적용
		
		rq.initOnBeforeActionInterceptor();
		// BeforeAction 인터셉터 실행 시 해당 메소드를 통해 Rq 호출
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
