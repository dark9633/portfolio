package com.portfolio.service;

import java.util.List;

import com.portfolio.domain.ReplyVO;


public interface ReplyService {

	/* 댓글 리스트 */
	public List<ReplyVO> ReplyList(Integer bNumber) throws Exception;
	
	/* 댓글 등록 */
	public int ReplyRegister(ReplyVO vo) throws Exception;
	
}
