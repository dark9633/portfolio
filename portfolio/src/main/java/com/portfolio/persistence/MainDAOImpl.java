package com.portfolio.persistence;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class MainDAOImpl implements MainDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.MainMapper";

	@Override
	public Date SampleSelectDate() throws Exception {
		return session.selectOne(namespace + ".SampleSelectDate");
	}

}
