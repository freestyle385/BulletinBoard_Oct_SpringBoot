package com.sbs.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.exam.demo.vo.Board;

@Mapper
public interface BoardRepository {
	
	public Board getBoardNameById(@Param("boardId") int boardId);

}
