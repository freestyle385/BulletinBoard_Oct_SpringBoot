package com.sbs.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.util.Util;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;

@Controller
public class UserArticleController {
	@Autowired // 등록된 컴포넌트를 자동으로 연결 해줌
	private ArticleService articleService;

	// 액션 메소드 시작
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		if (Util.isParamEmpty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요.");
		}
		if (Util.isParamEmpty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요.");
		}
		
		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body);
		int id = writeArticleRd.getData1();
		
		Article foundArticle = articleService.getFoundArticle(id);
		
		return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), foundArticle);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData getArticles() {
		List<Article> articles = articleService.getArticles();
		
		return ResultData.from("S-1", Util.f("전체 게시물이 조회되었습니다."), articles);
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article foundArticle = articleService.getFoundArticle(id);

		if (foundArticle == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		return ResultData.from("S-1", Util.f("%d번 게시물이 조회되었습니다.", id), foundArticle);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article foundArticle = articleService.getFoundArticle(id);

		if (foundArticle == null) {
			return id + "번 글은 존재하지 않습니다.";
		}

		articleService.deleteArticle(id);

		return id + "번 글이 삭제되었습니다.";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		Article foundArticle = articleService.getFoundArticle(id);
		
		if (foundArticle == null) {
			return id + "번 글은 존재하지 않습니다.";
		}

		articleService.modifyArticle(id, title, body);
		
		Article modifiedArticle = articleService.getFoundArticle(id);

		return id + "번 글이 수정되었습니다.<br>" + modifiedArticle;
	}
	// 액션 메소드 끝
}