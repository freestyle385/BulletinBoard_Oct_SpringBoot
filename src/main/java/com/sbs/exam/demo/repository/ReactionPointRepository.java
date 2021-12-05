package com.sbs.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReactionPointRepository {

	public void addIncreasingGoodRpInfo(@Param("boardId") int boardId, @Param("articleId") int articleId,
			@Param("memberId") int memberId);

	public void deleteGoodRpInfo(@Param("boardId") int boardId, @Param("articleId") int articleId,
			@Param("memberId") int memberId);

	public void addIncreasingBadRpInfo(@Param("boardId") int boardId, @Param("articleId") int articleId,
			@Param("memberId") int memberId);

	public void deleteBadRpInfo(@Param("boardId") int boardId, @Param("articleId") int articleId,
			@Param("memberId") int memberId);

	public Integer getRpInfoByMemberId(@Param("articleId") int articleId, @Param("memberId") int memberId);

}
