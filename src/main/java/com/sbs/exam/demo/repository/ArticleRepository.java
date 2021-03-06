package com.sbs.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public void writeArticle(@Param("memberId") int memberId, @Param("boardId") int boardId,
			@Param("title") String title, @Param("body") String body);

	public Article getForPrintArticle(@Param("id") int id);

	public List<Article> getForPrintArticles(@Param("boardId") int boardId,
			@Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword,
			@Param("limitStart") int limitStart, @Param("limitTake") int limitTake);

	public void deleteArticle(@Param("id") int id);

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	public int getLastInsertId();

	public int getArticlesCount(@Param("boardId") int boardId,
			@Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword);

	public void increaseHitCount(@Param("id") int id);

	public int getHitCount(@Param("id") int id);
	
	public void increaseGoodRp(@Param("id") int id);

	public void increaseBadRp(@Param("id") int id);

	public void decreaseGoodRp(@Param("id") int id);
	
	public void decreaseBadRp(@Param("id") int id);
	
	public int getGoodRpCount(@Param("id") int id);

	public int getBadRpCount(@Param("id") int id);

	public int getBoardIdByArticle(@Param("id") int id);

	

}
