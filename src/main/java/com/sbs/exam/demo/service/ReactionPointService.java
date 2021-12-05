package com.sbs.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ReactionPointRepository;
import com.sbs.exam.demo.vo.Rq;

@Service
public class ReactionPointService {
	private ReactionPointRepository reactionPointRepository;
	private ArticleService articleService;
	@Autowired
	private Rq rq;
	
	public ReactionPointService(ReactionPointRepository reactionPointRepository, ArticleService articleService,
			BoardService boardService) {
		this.reactionPointRepository = reactionPointRepository;
		this.articleService = articleService;
	}

	// detail.jsp의 ajax 관련 메서드
	public void increaseGoodRp(int id) {
		articleService.increaseGoodRp(id);
	}

	public void increaseBadRp(int id) {
		articleService.increaseBadRp(id);
	}

	public void decreaseGoodRp(int id) {
		articleService.decreaseGoodRp(id);
	}

	public void decreaseBadRp(int id) {
		articleService.decreaseBadRp(id);
	}

	public int getGoodRpCount(int id) {
		return articleService.getGoodRpCount(id);
	}

	public int getBadRpCount(int id) {
		return articleService.getBadRpCount(id);
	}

	// reactionPoint 테이블에 좋아요/싫어요 로그 기록 관련 메서드
	public void addIncreasingGoodRpInfo(int articleId, int memberId) {
		int boardId = articleService.getBoardIdByArticle(articleId);
		reactionPointRepository.addIncreasingGoodRpInfo(boardId, articleId, memberId);
	}
	
	public void addDecreasingGoodRpInfo(int articleId, int memberId) {
		int boardId = articleService.getBoardIdByArticle(articleId);
		reactionPointRepository.addDecreasingGoodRpInfo(boardId, articleId, memberId);
	}

	public void addIncreasingBadRpInfo(int articleId, int memberId) {
		int boardId = articleService.getBoardIdByArticle(articleId);
		reactionPointRepository.addIncreasingBadRpInfo(boardId, articleId, memberId);
	}
	
	public void addDecreasingBadRpInfo(int articleId, int memberId) {
		int boardId = articleService.getBoardIdByArticle(articleId);
		reactionPointRepository.addDecreasingBadRpInfo(boardId, articleId, memberId);
	}

	public Integer getRpInfoByMemberId(int articleId, int memberId) {
		Integer sumPointByMemberId = reactionPointRepository.getRpInfoByMemberId(articleId, memberId);
		// 로그인 상태가 아닐 경우, 임의로 99값을 부여
		if (sumPointByMemberId == null) {
			sumPointByMemberId = 99;
		}
		
		return (int) sumPointByMemberId;
	}

	public boolean isAlreadyAddGoodRp(int articleId) {
		// 좋아요/취소 = 1/-1, 싫어요/취소 = 2/-2
		// 현재 게시물에서, loginedMemberId의 pointTypeCode값의 합이 1이면 좋아요 상태
		int sumPointByMemberId = getRpInfoByMemberId(articleId, rq.getLoginedMemberId());
		
		if (sumPointByMemberId == 1) {
			return true;
		}
		return false;
	}

	public boolean isAlreadyAddBadRp(int articleId) {
		// 좋아요/취소 = 1/-1, 싫어요/취소 = 2/-2
		// 현재 게시물에서, loginedMemberId의 pointTypeCode값의 합이 2이면 싫어요 상태
		int sumPointByMemberId = getRpInfoByMemberId(articleId, rq.getLoginedMemberId());
		
		if (sumPointByMemberId == 2) {
			return true;
		}
		return false;
	}

}
