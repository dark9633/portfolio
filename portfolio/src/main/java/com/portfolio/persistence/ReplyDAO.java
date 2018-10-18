package com.portfolio.persistence;

import java.util.List;

import com.portfolio.domain.ReplyVO;


public interface ReplyDAO {

	/* 댓글 리스트 */
	public List<ReplyVO> ReplyList(Integer bNumber) throws Exception;
	
}
