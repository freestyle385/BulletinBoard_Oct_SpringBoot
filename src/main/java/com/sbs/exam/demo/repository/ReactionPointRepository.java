package com.sbs.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReactionPointRepository {

	public void addGoodRpInfo(@Param("boardId") int boardId, @Param("articleId") int articleId, @Param("memberId") int memberId);

	public void addBadRpInfo(@Param("boardId") int boardId, @Param("articleId") int articleId, @Param("memberId") int memberId);
	
	
}
