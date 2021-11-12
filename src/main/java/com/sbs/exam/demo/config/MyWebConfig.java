package com.sbs.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sbs.exam.demo.interceptor.BeforeActionInterceptor;

@Configuration
public class MyWebConfig implements WebMvcConfigurer{
	
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		InterceptorRegistration ir = registry.addInterceptor(beforeActionInterceptor);
		ir.addPathPatterns("/**").excludePathPatterns("/resource/**");
		// addPathPatterns("/**")는 전체 url을 인터셉트하게함. excludePathPatterns는 그 중 예외할 것(이미지와 같은 정적 자원).
		// 추가할 것은 뒤에 .붙인 후 쭉 이어서 적어나갈 수 있음.
	}
}
