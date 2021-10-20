package com.sbs.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.vo.Article;

@Service
public class ArticleService {
	// 인스턴스 변수
	private int articleLastId;
	private List<Article> articles;

	// 생성자
	public ArticleService() {
		articleLastId = 0;
		articles = new ArrayList<>();
		
		makeTestData();
	}
	
	public void makeTestData() {
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

	public Article getFoundArticle(int id) {
		for (Article foundArticle : articles) {
			if (foundArticle.getId() == id) {
				return foundArticle;
			}
		}
		return null;
	}
	
	public List<Article> getArticles() {
		return articles;
	}
	
	public void deleteArticle(int id) {
		Article foundArticle = getFoundArticle(id);

		articles.remove(foundArticle);
	}

	public Article modifyArticle(int id, String title, String body) {
		Article foundArticle = getFoundArticle(id);

		foundArticle.setTitle(title);
		foundArticle.setBody(body);

		return foundArticle;
	}
	
}
