package com.portfolio.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.domain.ChatVO;
import com.portfolio.domain.LogVO;
import com.portfolio.persistence.EtcDAO;


@Service
public class EtcServiceImpl implements EtcService{

	@Inject private EtcDAO dao;

	@Override
	public int ChattingRegister(ChatVO vo) throws Exception {
		return dao.ChattingRegister(vo);
	}

	@Override
	public List<ChatVO> ChattingList() throws Exception {
		return dao.ChattingList();
	}

	@Override
	public void LogRegister(LogVO vo) throws Exception {
		dao.LogRegister(vo);
	}

	@Override
	public List<Map<?, ?>> LogListLog() throws Exception {
		return dao.LogListLog();
	}

	@Override
	public List<Map<?, ?>> LogListBrowser() throws Exception {
		return dao.LogListBrowser();
	}

	@Override
	public List<Map<?, ?>> LogListDevice() throws Exception {
		return dao.LogListDevice();
	}

	@Override
	public List<Map<?, ?>> LogListUrl() throws Exception {
		return dao.LogListUrl();
	}
	
}
