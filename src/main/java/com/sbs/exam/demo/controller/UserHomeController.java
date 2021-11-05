package com.sbs.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserHomeController {
	
	@RequestMapping("/usr/home/main")
	public String showMain() {
		return "/usr/home/main";
		// application.yml에 /WEB-INF/jsp/는 prifix, .jsp는 surfix로 설정되어 있으므로 생략 가능
	}

	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}

}