package com.sbs.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

	// 액션 메소드 시작
	@RequestMapping("/usr/article/write")
	public String showWrite() {

		return "usr/article/write";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (Util.isParamEmpty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요.");
		}
		if (Util.isParamEmpty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요.");
		}

		int articleId = articleService.writeArticle((int) rq.getLoginedMemberId(), title, body);

		return Util.jsReplace(Util.f("%d번 글이 생성되었습니다.", articleId), Util.f("/usr/article/detail?id=%d", articleId));
	}

	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq) req.getAttribute("rq");

		List<Article> articles = articleService.getForPrintArticles(id);
		Board board = boardService.getBoardNameById(id);
		int articlesCount = articleService.getArticlesCount(id);

		if (board == null) {
			return rq.historyBackOnView("해당 게시판은 존재하지 않습니다.");
		}

		model.addAttribute("articles", articles);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("board", board);

		return "/usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {
		Article foundArticle = articleService.getForPrintArticle(id);

		model.addAttribute("foundArticle", foundArticle);

		return "/usr/article/detail";
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {

		Rq rq = (Rq) req.getAttribute("rq");

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
	public String showModify(Model model, HttpServletRequest req, int id, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");

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