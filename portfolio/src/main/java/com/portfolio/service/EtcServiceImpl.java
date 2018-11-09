package com.portfolio.service;

import java.util.List;

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
	
}
