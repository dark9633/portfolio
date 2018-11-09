package com.portfolio.service;

import java.util.List;
import java.util.Map;

import com.portfolio.domain.ChatVO;
import com.portfolio.domain.LogVO;


public interface EtcService {

	/* 채팅 등록 */
	public int ChattingRegister(ChatVO vo) throws Exception;
	
	/* 채팅 리스트 */
	public List<ChatVO> ChattingList() throws Exception;
	
	/* 사이트 로그 등록*/
	public void LogRegister(LogVO vo) throws Exception;
	
	/* 사이트 로그 리스트 */
	public List<Map<?, ?>> LogListLog() throws Exception;
	
	/* 사이트 로그 리스트 브라우저 */
	public List<Map<?, ?>> LogListBrowser() throws Exception;
	
	/* 사이트 로그 리스트 디바이스 */
	public List<Map<?, ?>> LogListDevice() throws Exception;
	
	/* 사이트 로그 URL */
	public List<Map<?, ?>> LogListUrl() throws Exception;
	
}
