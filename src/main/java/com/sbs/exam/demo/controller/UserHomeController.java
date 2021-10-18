package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Controller
public class UserHomeController {
	
	@RequestMapping("/usr/home/getString")
	@ResponseBody
	public String getString() {
		return "Hi";
	}
	
	@RequestMapping("/usr/home/getInt")
	@ResponseBody
	public int getInt() {
		return 10;
	}
	
	@RequestMapping("/usr/home/getFloat")
	@ResponseBody
	public float getFloat() {
		return 1.14f;
	}
	
	@RequestMapping("/usr/home/getDouble")
	@ResponseBody
	public double getDouble() {
		return 1.14;
	}
	
	@RequestMapping("/usr/home/getBoolean")
	@ResponseBody
	public boolean getBoolean() {
		return true;
	}
	
	@RequestMapping("/usr/home/getChar")
	@ResponseBody
	public char getChar() {
		return 'a';
	}
	
	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map getMap() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("철수의 나이", 22);
		map.put("영희의 나이", 33);
		
		return map;
	}
	
	@RequestMapping("/usr/home/getList")
	@ResponseBody
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		
		list.add("철수의 나이");
		list.add("영희의 나이");
		
		return list;
	}
	
	@RequestMapping("/usr/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article article = new Article(); // @NoArgsConstructor로 인해 인자를 받지 않아도 됨
		Article article1 = new Article(1, "제목1"); // @AllArgsConstructor로 인해 인자를 받을 수 있음
		
		return article;
	}
	
	@RequestMapping("/usr/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		Article article1 = new Article(1, "제목1");
		Article article2 = new Article(2, "제목2");
		
		List<Article> list = new ArrayList<>();

		list.add(article1);
		list.add(article2);

		return list;
	}

}

//@Data // Article 전체를 @Data를 통해 값을 가져옴(private도 가져옴)
//class Article {
//	// @Getter를 쓰면 private이어도 값을 가져올 수 있음
//	private int id;
//	private String title;
//	
//	public Article() {
//		id = 1;
//		title = "제목";
//	}
//}

@Data // Article 전체를 @Data를 통해 값을 가져옴(private도 가져옴)
@AllArgsConstructor
@NoArgsConstructor
class Article {
	// @Getter를 쓰면 private이어도 값을 가져올 수 있음
	private int id;
	private String title;
	
}


