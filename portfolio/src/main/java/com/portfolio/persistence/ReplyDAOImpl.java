package com.portfolio.persistence;


import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.portfolio.domain.ReplyVO;


@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.ReplyMapper";

	@Override
	public List<ReplyVO> ReplyList(Integer bNumber) throws Exception {
		return session.selectList(namespace + ".ReplyList", bNumber);
	}

	@Override
	public int ReplyRegister(ReplyVO vo) throws Exception {
		return session.insert(namespace + ".ReplyRegister", vo);
	}

	@Override
	public int ReplyDelete(Integer reNumber) throws Exception {
		return session.delete(namespace + ".ReplyDelete", reNumber);
	}

	@Override
	public int ReplyUpdate(ReplyVO vo) throws Exception {
		return session.update(namespace + ".ReplyUpdate", vo);
	}

	@Override
	public ReplyVO ReplyView(Integer reNumber) throws Exception {
		return session.selectOne(namespace + ".ReplyView", reNumber);
	}

}
