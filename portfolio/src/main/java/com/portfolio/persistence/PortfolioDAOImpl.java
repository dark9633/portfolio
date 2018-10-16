package com.portfolio.persistence;


import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class PortfolioDAOImpl implements PortfolioDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.PortfolioMapper";

}
