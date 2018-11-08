package com.portfolio.persistence;

import java.util.List;

import com.portfolio.domain.ChatVO;


public interface EtcDAO {

	/* 채팅 등록 */
	public int ChattingRegister(ChatVO vo) throws Exception;
	
	/* 채팅 리스트 */
	public List<ChatVO> ChattingList() throws Exception;
	
}
