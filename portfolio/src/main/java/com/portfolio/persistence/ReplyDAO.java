package com.portfolio.persistence;

import java.util.List;

import com.portfolio.domain.ReplyVO;


public interface ReplyDAO {

	/* 댓글 리스트 */
	public List<ReplyVO> ReplyList(Integer bNumber) throws Exception;
	
	/* 댓글 등록 */
	public int ReplyRegister(ReplyVO vo) throws Exception;
	
}
