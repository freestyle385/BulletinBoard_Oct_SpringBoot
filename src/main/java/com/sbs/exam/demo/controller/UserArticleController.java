package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.vo.Article;

@Controller
public class UserArticleController {
	private int articleLastId;
	private List<Article> articles;

	public UserArticleController() {
		articleLastId = 0;
		articles = new ArrayList<>();
		
		makeTestData();
	}

	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			
			writeArticle(title, body);
		}
	}

	public Article writeArticle(String title, String body) {
		int id = articleLastId + 1;
		Article article = new Article(id, title, body);

		articles.add(article);
		articleLastId = id;
		
		return article;
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = writeArticle(title, body);

		return article;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articles;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article foundArticle = null;
		int foundIndex = -1;
		
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			
			if (article.getId() == id) {
				foundIndex = i;
				break;
			}
		}
		
		if (foundIndex == -1) {
			return id + "번 글은 존재하지 않습니다.";
		} else {
			articles.remove(foundIndex);
			return id + "번 글이 삭제되었습니다.";
		}
	}
	
}