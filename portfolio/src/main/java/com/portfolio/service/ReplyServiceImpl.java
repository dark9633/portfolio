package com.portfolio.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.domain.ReplyVO;
import com.portfolio.persistence.ReplyDAO;


@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject private ReplyDAO dao;

	@Override
	public List<ReplyVO> ReplyList(Integer bNumber) throws Exception {
		return dao.ReplyList(bNumber);
	}

	@Override
	public int ReplyRegister(ReplyVO vo) throws Exception {
		return dao.ReplyRegister(vo);
	}

	@Override
	public int ReplyDelete(Integer reNumber) throws Exception {
		return dao.ReplyDelete(reNumber);
	}

	@Override
	public int ReplyUpdate(ReplyVO vo) throws Exception {
		return dao.ReplyUpdate(vo);
	}
	
}
