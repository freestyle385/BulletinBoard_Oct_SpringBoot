package com.sbs.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.service.BoardService;
import com.sbs.exam.demo.util.Util;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.Board;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UserArticleController {
	@Autowired // 등록된 컴포넌트를 자동으로 연결 해줌
	private ArticleService articleService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private Rq rq;

	// 액션 메소드 시작
	@RequestMapping("/usr/article/write")
	public String showWrite() {

		return "usr/article/write";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(Integer boardId, String title, String body) {

		if (boardId == null) {
			return Util.jsHistoryBack("게시판을 선택해주세요.");
		}
		if (Util.isParamEmpty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요.");
		}
		if (Util.isParamEmpty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요.");
		}

		int articleId = articleService.writeArticle((int) rq.getLoginedMemberId(), boardId, title, body);

		return Util.jsReplace(Util.f("%d번 글이 생성되었습니다.", articleId), Util.f("/usr/article/detail?id=%d", articleId));
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model, @RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "title, body") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "1") int page) {

		Board board = boardService.getBoardNameById(boardId);

		if (board == null) {
			return rq.historyBackOnView("해당 게시판은 존재하지 않습니다.");
		}

		int articlesCount = articleService.getArticlesCount(boardId, searchKeywordTypeCode, searchKeyword);

		int itemsCountInApage = 10; // 페이지별 게시물 개수
		int pagesCount = articleService.getPagesCount(itemsCountInApage, articlesCount);
		// 총 페이지 개수

		List<Article> articles = articleService.getForPrintArticles(boardId, itemsCountInApage, page, searchKeywordTypeCode, searchKeyword);

		model.addAttribute("articles", articles);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("board", board);

		return "/usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {
		
		if (articleService.isArticleExists(id) == false) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
//		articleService.increaseHitCount(id);
		
		Article foundArticle = articleService.getForPrintArticle(id);
		
		model.addAttribute("foundArticle", foundArticle);

		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doIncreaseHitCount")
	@ResponseBody
	public int doIncreaseHitCount(int id) {
		articleService.increaseHitCount(id);
		int hitCount = articleService.getHitCount(id);
		
		return hitCount;
	}
	
	@RequestMapping("/usr/article/increaseGoodRp")
	@ResponseBody
	public int increaseGoodRp(int id) {
		articleService.increaseGoodRp(id);
		int goodRp = articleService.getGoodRpCount(id);
		
//		reactionPointService.updateGoodRpInfo(id, (int) rq.getLoginedMemberId());
		
		return goodRp;
	}
	
	@RequestMapping("/usr/article/increaseBadRp")
	@ResponseBody
	public int increaseBadRp(int id) {
		articleService.increaseBadRp(id);
		int badRp = articleService.getBadRpCount(id);
		
//		reactionPointService.updateGoodRpInfo(id, (int) rq.getLoginedMemberId());
		
		return badRp;
	}
	
	@RequestMapping("/usr/article/decreaseGoodRp")
	@ResponseBody
	public int decreaseGoodRp(int id) {
		articleService.decreaseGoodRp(id);
		int goodRp = articleService.getGoodRpCount(id);
		
//		reactionPointService.updateGoodRpInfo(id, (int) rq.getLoginedMemberId());
		
		return goodRp;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		if (articleService.isArticleExists(id) == false) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		if (articleService.isUsrAuthorized(rq, id) == false) {
			return Util.jsHistoryBack("해당 게시물에 권한이 없습니다.");
		}

		articleService.deleteArticle(id);

		return Util.jsReplace(Util.f("%d번 게시물이 삭제되었습니다.", id), "list");
	}

	@RequestMapping("/usr/article/modify")
	public String showModify(Model model, int id, String title, String body) {

		if (articleService.isArticleExists(id) == false) {
			return rq.historyBackOnView(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		if (articleService.isUsrAuthorized(rq, id) == false) {
			return rq.historyBackOnView("해당 게시물에 권한이 없습니다.");
		}

		Article foundArticle = articleService.getForPrintArticle(id);
		model.addAttribute("foundArticle", foundArticle);

		return "/usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		articleService.modifyArticle(id, title, body);

		return Util.jsReplace(Util.f("%d번 게시물이 수정되었습니다.", id), "list");
	}
	// 액션 메소드 끝
}