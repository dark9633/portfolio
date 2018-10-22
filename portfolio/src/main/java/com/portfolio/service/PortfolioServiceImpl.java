package com.portfolio.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.domain.Criteria;
import com.portfolio.domain.PortfolioVO;
import com.portfolio.persistence.PortfolioDAO;


@Service
public class PortfolioServiceImpl implements PortfolioService{

	@Inject private PortfolioDAO dao;

	@Override
	public List<PortfolioVO> PortfolioList(Criteria cri) throws Exception {
		return dao.PortfolioList(cri);
	}

	@Override
	public int PortfolioListCount(Criteria cri) throws Exception {
		return dao.PortfolioListCount(cri);
	}

	@Override
	public int PortfolioRegister(PortfolioVO vo) throws Exception {
		return dao.PortfolioRegister(vo);
	}
	
}
