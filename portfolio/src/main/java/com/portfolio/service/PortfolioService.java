package com.portfolio.service;

import java.util.List;

import com.portfolio.domain.Criteria;
import com.portfolio.domain.PortfolioVO;


public interface PortfolioService {

	/* 포트폴리오 리스트 */
	public List<PortfolioVO> PortfolioList(Criteria cri) throws Exception;
	
	/* 포트폴리오 리스트 카운트 */
	public int PortfolioListCount(Criteria cri) throws Exception;

	/* 포트폴리오 등록 */
	public int PortfolioRegister(PortfolioVO vo) throws Exception;
	
	/* 포트폴리오 상세 */
	public PortfolioVO PortfolioView(Integer pfNumber) throws Exception;
	
	/* 포트폴리오 수정 */
	public int PortfolioModify(PortfolioVO vo) throws Exception;

	/* 포트폴리오 삭제 */
	public int PortfolioDelete(Integer pfNumber) throws Exception;
	
}
