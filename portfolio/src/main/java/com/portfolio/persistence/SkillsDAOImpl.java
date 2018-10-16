package com.portfolio.persistence;


import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class SkillsDAOImpl implements SkillsDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.SkillsMapper";

}
