package com.sbs.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.Rq;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public int writeArticle(int memberId, int boardId, String title, String body) {
		articleRepository.writeArticle(memberId, boardId, title, body);
		return articleRepository.getLastInsertId();
	}

	public Article getForPrintArticle(int id) {
		return articleRepository.getForPrintArticle(id);
	}

	public List<Article> getForPrintArticles(int boardId, int itemsCountInApage, int page) {

		int limitStart = (page - 1) * itemsCountInApage; // 페이징 범위 시작지점
		int limitTake = itemsCountInApage;

		return articleRepository.getForPrintArticles(boardId, limitStart, limitTake);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

	public boolean isUsrAuthorized(Rq rq, int id) {
		Article foundArticle = getForPrintArticle(id);

		if (rq.getLoginedMemberId() != foundArticle.getMemberId()) {
			return false;
		}
		return true;
	}

	public boolean isArticleExists(int id) {
		Article foundArticle = getForPrintArticle(id);

		if (foundArticle == null) {
			return false;
		}
		return true;
	}

	public int getArticlesCount(int boardId) {
		return articleRepository.getArticlesCount(boardId);
	}

	public int getPagesCount(int itemsCountInApage, int articlesCount) {
		int pagesCount = (int) Math.ceil((double) articlesCount / itemsCountInApage);
		
		return pagesCount;
	}

}
