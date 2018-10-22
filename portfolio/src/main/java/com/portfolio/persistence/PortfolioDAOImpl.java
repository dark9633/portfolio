package com.portfolio.persistence;


import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.portfolio.domain.Criteria;
import com.portfolio.domain.PortfolioVO;


@Repository
public class PortfolioDAOImpl implements PortfolioDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.PortfolioMapper";

	@Override
	public List<PortfolioVO> PortfolioList(Criteria cri) throws Exception {
		return session.selectList(namespace + ".PortfolioList", cri);
	}

	@Override
	public int PortfolioListCount(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".PortfolioListCount", cri);
	}

	@Override
	public int PortfolioRegister(PortfolioVO vo) throws Exception {
		return session.insert(namespace + ".PortfolioRegister", vo);
	}

}
