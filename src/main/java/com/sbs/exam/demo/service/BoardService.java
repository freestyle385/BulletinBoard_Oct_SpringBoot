package com.sbs.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.BoardRepository;
import com.sbs.exam.demo.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public Board getBoardNameById(int boardId) {
		return boardRepository.getBoardNameById(boardId);
	}
}
