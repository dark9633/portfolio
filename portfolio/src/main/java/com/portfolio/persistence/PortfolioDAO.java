package com.portfolio.persistence;

import java.util.List;

import com.portfolio.domain.Criteria;
import com.portfolio.domain.PortfolioVO;


public interface PortfolioDAO {

	/* 포트폴리오 리스트 */
	public List<PortfolioVO> PortfolioList(Criteria cri) throws Exception;
	
	/* 포트폴리오 리스트 카운트 */
	public int PortfolioListCount(Criteria cri) throws Exception;
	
	/* 포트폴리오 등록 */
	public int PortfolioRegister(PortfolioVO vo) throws Exception;
	
}
