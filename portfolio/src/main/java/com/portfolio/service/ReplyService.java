package com.portfolio.service;

import java.util.List;

import com.portfolio.domain.ReplyVO;


public interface ReplyService {

	/* 댓글 리스트 */
	public List<ReplyVO> ReplyList(Integer bNumber) throws Exception;
	
}
