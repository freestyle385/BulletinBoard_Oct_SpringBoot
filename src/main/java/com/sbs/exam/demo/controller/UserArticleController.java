package com.sbs.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.vo.Article;

@Controller
public class UserArticleController {
	@Autowired // 등록된 컴포넌트를 자동으로 연결 해줌
	private ArticleService articleService;

	// 액션 메소드 시작
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = articleService.writeArticle(title, body);
		Article foundArticle = articleService.getFoundArticle(id);
		return foundArticle;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public String getArticle(int id) {
		Article foundArticle = articleService.getFoundArticle(id);

		if (foundArticle == null) {
			return id + "번 글은 존재하지 않습니다.";
		}

		return "게시물 번호 : " + foundArticle.getId() + "<br>게시물 제목 : " + foundArticle.getTitle() + "<br>게시물 내용 : "
				+ foundArticle.getBody();
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
		
//		if (title == null) {
//			title = foundArticle.getTitle();
//		}
//		if (body == null) {
//			body = foundArticle.getBody();
//		}

		articleService.modifyArticle(id, title, body);
		
		Article modifiedArticle = articleService.getFoundArticle(id);

		return id + "번 글이 수정되었습니다.<br>" + modifiedArticle;
	}
	// 액션 메소드 끝
}