package com.portfolio.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.portfolio.domain.ChatVO;
import com.portfolio.domain.LogVO;


@Repository
public class EtcDAOImpl implements EtcDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.EtcMapper";

	@Override
	public int ChattingRegister(ChatVO vo) throws Exception {
		return session.insert(namespace + ".ChattingRegister", vo);
	}

	@Override
	public List<ChatVO> ChattingList() throws Exception {
		return session.selectList(namespace + ".ChattingList");
	}

	@Override
	public void LogRegister(LogVO vo) throws Exception {
		session.insert(namespace + ".LogRegister", vo);
	}


}
