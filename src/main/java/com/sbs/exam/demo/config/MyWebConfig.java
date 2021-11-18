package com.sbs.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sbs.exam.demo.interceptor.BeforeActionInterceptor;
import com.sbs.exam.demo.interceptor.NeedLoginInterceptor;
import com.sbs.exam.demo.interceptor.NeedLogoutInterceptor;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
	// BeforeActionInterceptor 인터셉터 불러오기
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	// needLoginInterceptor 인터셉터 불러오기
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;
	// needLogoutInterceptor 인터셉터 불러오기
	@Autowired
	NeedLogoutInterceptor needLogoutInterceptor;

	// 인터셉터에 적용하는 메서드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		InterceptorRegistration beforeActionIr = registry.addInterceptor(beforeActionInterceptor);
		beforeActionIr.addPathPatterns("/**").excludePathPatterns("/resource/**");
		// addPathPatterns("/**")는 전체 url을 인터셉트하게함. excludePathPatterns는 그 중 예외할 것(이미지와
		// 같은 정적 자원).
		// 추가할 것은 뒤에 .붙인 후 쭉 이어서 적어나갈 수 있음.

		InterceptorRegistration needLoginIr = registry.addInterceptor(needLoginInterceptor);
		needLoginIr.addPathPatterns("/usr/article/write").addPathPatterns("/usr/article/doWrite").addPathPatterns("/usr/article/modify")
				.addPathPatterns("/usr/article/doModify").addPathPatterns("/usr/article/doDelete")
				.addPathPatterns("/usr/member/doLogout");

		InterceptorRegistration needLogoutIr = registry.addInterceptor(needLogoutInterceptor);
		needLogoutIr.addPathPatterns("/usr/member/login").addPathPatterns("/usr/member/join")
				.addPathPatterns("/usr/member/doLogin").addPathPatterns("/usr/member/doJoin");
	}
}
