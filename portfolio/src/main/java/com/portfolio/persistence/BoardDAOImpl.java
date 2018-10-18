package com.portfolio.persistence;


import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.portfolio.domain.BoardVO;


@Repository
public class BoardDAOImpl implements BoardDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.BoardMapper";

	@Override
	public int BoardRegister(BoardVO vo) throws Exception {
		return session.insert(namespace + ".BoardRegister", vo);
	}

}
