package com.sbs.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.util.Util;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UserArticleController {
	@Autowired // 등록된 컴포넌트를 자동으로 연결 해줌
	private ArticleService articleService;
	
	// 액션 메소드 시작
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpServletRequest req, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");

		if (Util.isParamEmpty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요.");
		}
		if (Util.isParamEmpty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요.");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		int id = writeArticleRd.getData1();

		Article foundArticle = articleService.getForPrintArticle(id);

		return ResultData.newData(writeArticleRd, "article", foundArticle);
	}

//	@RequestMapping("/usr/article/getArticles")
//	@ResponseBody
//	public ResultData<List<Article>> getArticles() {
//		List<Article> articles = articleService.getArticles();
//
//		return ResultData.from("S-1", "전체 게시물이 조회되었습니다.", "articles", articles);
//	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getForPrintArticles();

		model.addAttribute("articles", articles);

		return "/usr/article/list";
	}

//	@RequestMapping("/usr/article/getArticle")
//	@ResponseBody
//	public ResultData<Article> getArticle(int id) {
//		Article foundArticle = articleService.getFoundArticle(id);
//
//		if (foundArticle == null) {
//			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
//		}
//
//		return ResultData.from("S-1", Util.f("%d번 게시물이 조회되었습니다.", id), "article", foundArticle);
//	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {
		Article foundArticle = articleService.getForPrintArticle(id);

		model.addAttribute("foundArticle", foundArticle);

		return "/usr/article/detail";
	}

//	@RequestMapping("/usr/article/doDelete")
//	@ResponseBody
//	public ResultData<Integer> doDelete(HttpSession httpSession, int id) {
//
//		if (memberService.isLogined(httpSession) == false) {
//			return ResultData.from("F-A", "로그인 후 이용해주세요.");
//		}
//
//		if (articleService.isArticleExists(id) == false) {
//			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
//		}
//
//		if (articleService.isUsrAuthorized(httpSession, id) == false) {
//			return ResultData.from("F-B", "해당 게시물에 권한이 없습니다.");
//		}
//
//		articleService.deleteArticle(id);
//
//		return ResultData.from("S-1", Util.f("%d번 게시물이 삭제되었습니다.", id), "id", id);
//	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");

		if (articleService.isArticleExists(id) == false) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		if (articleService.isUsrAuthorized(rq, id) == false) {
			return Util.jsHistoryBack(Util.f("해당 게시물에 권한이 없습니다."));
		}

		articleService.deleteArticle(id);

		return Util.jsReplace(Util.f("%d번 게시물이 삭제되었습니다.", id), "list");
	}

	@RequestMapping("/usr/article/modify")
	public String modify(Model model, HttpServletRequest req, int id, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (articleService.isArticleExists(id) == false) {
			return rq.historyBackOnView(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		if (articleService.isUsrAuthorized(rq, id) == false) {
			return rq.historyBackOnView(Util.f("해당 게시물에 권한이 없습니다."));
		}
		
		Article foundArticle = articleService.getForPrintArticle(id);
		model.addAttribute("foundArticle", foundArticle);

		return "/usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		articleService.modifyArticle(id, title, body);

		return Util.jsReplace(Util.f("%d번 게시물이 수정되었습니다.", id), "list");
	}
	// 액션 메소드 끝
}