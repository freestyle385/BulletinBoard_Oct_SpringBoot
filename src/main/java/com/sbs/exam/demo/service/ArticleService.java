package com.sbs.exam.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.util.Util;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public ResultData<Integer> writeArticle(int memberId, String title, String body) {
		articleRepository.writeArticle(memberId, title, body);
		int id = articleRepository.getLastInsertId();

		return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다.", id), "id", id);
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

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

	public boolean isUsrAuthorized(HttpSession httpSession, int id) {
		int loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		Article foundArticle = getFoundArticle(id);
		
		if (loginedMemberId != foundArticle.getMemberId()) {
			return false;
		}
		return true;
	}

	public boolean isArticleExists(int id) {
		Article foundArticle = getFoundArticle(id);
		
		if (foundArticle == null) {
			return false;
		}
		return true;
	}
}
