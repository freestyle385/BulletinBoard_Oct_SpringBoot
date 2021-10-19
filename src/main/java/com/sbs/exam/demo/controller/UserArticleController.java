package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.vo.Article;

@Controller
public class UserArticleController {
	// 인스턴스 변수
	private int articleLastId;
	private List<Article> articles;

	// 생성자
	public UserArticleController() {
		articleLastId = 0;
		articles = new ArrayList<>();

		makeTestData();
	}

	// 서비스 메소드 시작
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

	private Article getFoundArticle(int id) {
		for (Article foundArticle : articles) {
			if (foundArticle.getId() == id) {
				return foundArticle;
			}
		}
		return null;
	}

	private void deleteArticle(int id) {
		Article foundArticle = getFoundArticle(id);

		articles.remove(foundArticle);
	}

	private Article modifyArticle(int id, String title, String body) {
		Article foundArticle = getFoundArticle(id);

		foundArticle.setTitle(title);
		foundArticle.setBody(body);

		return foundArticle;
	}
	// 서비스 메소드 끝

	// 액션 메소드 시작
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
		Article foundArticle = getFoundArticle(id);

		if (foundArticle == null) {
			return id + "번 글은 존재하지 않습니다.";
		}

		deleteArticle(id);

		return id + "번 글이 삭제되었습니다.";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		Article foundArticle = getFoundArticle(id);

		if (foundArticle == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		
		foundArticle = modifyArticle(id, title, body);

		return id + "번 글이 수정되었습니다.<br>" + foundArticle;
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public String getArticle(int id) {
		Article foundArticle = getFoundArticle(id);

		if (foundArticle == null) {
			return id + "번 글은 존재하지 않습니다.";
		}

		return "게시물 번호 : " + foundArticle.getId() + "<br>게시물 제목 : " + foundArticle.getTitle() + "<br>게시물 내용 : " + foundArticle.getBody();
	}
	// 액션 메소드 끝
}