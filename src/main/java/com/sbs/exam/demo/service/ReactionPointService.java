package com.sbs.exam.demo.service;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ReactionPointRepository;

@Service
public class ReactionPointService {
	private ReactionPointRepository reactionPointRepository;
	private ArticleService articleService;
	private BoardService boardService;

	public ReactionPointService(ReactionPointRepository reactionPointRepository, ArticleService articleService, BoardService boardService) {
		this.reactionPointRepository = reactionPointRepository;
		this.articleService = articleService;
		this.boardService = boardService;
	}
	
	public void increaseGoodRp(int id) {
		articleService.increaseGoodRp(id);
	}
	
	public void increaseBadRp(int id) {
		articleService.increaseBadRp(id);
	}
	
	public void decreaseGoodRp(int id) {
		articleService.decreaseGoodRp(id);
	}

	public int getGoodRpCount(int id) {
		return articleService.getGoodRpCount(id);
	}
	
	public int getBadRpCount(int id) {
		return articleService.getBadRpCount(id);
	}
	
	public void addGoodRpInfo(int articleId, int memberId) {
		int boardId = articleService.getBoardIdByArticle(articleId);
		reactionPointRepository.addGoodRpInfo(boardId, articleId, memberId);
	}

	public void addBadRpInfo(int articleId, int memberId) {
		int boardId = articleService.getBoardIdByArticle(articleId);
		reactionPointRepository.addBadRpInfo(boardId, articleId, memberId);
	}

}
