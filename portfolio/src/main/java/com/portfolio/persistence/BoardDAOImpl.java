package com.portfolio.persistence;


import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.portfolio.domain.BoardVO;
import com.portfolio.domain.Criteria;


@Repository
public class BoardDAOImpl implements BoardDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.BoardMapper";

	@Override
	public int BoardRegister(BoardVO vo) throws Exception {
		return session.insert(namespace + ".BoardRegister", vo);
	}

	@Override
	public List<BoardVO> BoardList(Criteria cri) throws Exception {
		return session.selectList(namespace + ".BoardList", cri);
	}

	@Override
	public int BoardListCount(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".BoardListCount", cri);
	}

	@Override
	public BoardVO BoardView(Integer bNumber) throws Exception {
		return session.selectOne(namespace + ".BoardView", bNumber);
	}

}
