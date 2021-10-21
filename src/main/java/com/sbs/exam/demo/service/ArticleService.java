package com.sbs.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.vo.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		articleRepository.makeTestData();
	}
	
	public Article writeArticle(String title, String body) {
		return articleRepository.writeArticle(title, body);
	}

	public Article getFoundArticle(int id) {
		return articleRepository.getFoundArticle(id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public Article modifyArticle(int id, String title, String body) {
		return articleRepository.modifyArticle(id, title, body);
	}
}
