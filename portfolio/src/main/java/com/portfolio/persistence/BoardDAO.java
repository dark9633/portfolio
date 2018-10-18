package com.portfolio.persistence;

import com.portfolio.domain.BoardVO;


public interface BoardDAO {

	/* 게시글 등록 */
	public int BoardRegister(BoardVO vo) throws Exception;
	
}
