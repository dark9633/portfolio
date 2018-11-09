package com.portfolio.service;

import java.util.List;

import com.portfolio.domain.ChatVO;
import com.portfolio.domain.LogVO;


public interface EtcService {

	/* 채팅 등록 */
	public int ChattingRegister(ChatVO vo) throws Exception;
	
	/* 채팅 리스트 */
	public List<ChatVO> ChattingList() throws Exception;
	
	/* 사이트 로그 */
	public void LogRegister(LogVO vo) throws Exception;
	
}
