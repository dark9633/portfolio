package com.portfolio.service;

import com.portfolio.domain.BoardVO;


public interface BoardService {

	/* 게시글 등록 */
	public int BoardRegister(BoardVO vo) throws Exception;
	
}
