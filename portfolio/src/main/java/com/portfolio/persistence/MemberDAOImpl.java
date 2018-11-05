package com.portfolio.persistence;


import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.portfolio.domain.MemberVO;


@Repository
public class MemberDAOImpl implements MemberDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.MemberMapper";

	@Override
	public int MemberEmailExistCheck(MemberVO vo) throws Exception {
		return session.selectOne(namespace + ".MemberEmailExistCheck", vo);
	}

	@Override
	public int MemberNickNameExistCheck(MemberVO vo) throws Exception {
		return session.selectOne(namespace + ".MemberNickNameExistCheck", vo);
	}

	@Override
	public int MemberRegister(MemberVO vo) throws Exception {
		return session.insert(namespace + ".MemberRegister", vo);
	}

	@Override
	public MemberVO MemberLogin(MemberVO vo) throws Exception {
		return session.selectOne(namespace + ".MemberLogin", vo);
	}

	@Override
	public MemberVO MemberLoginNaver(MemberVO vo) throws Exception {
		return session.selectOne(namespace + ".MemberLoginNaver", vo);
	}

	@Override
	public int MemberRegisterNaver(MemberVO vo) throws Exception {
		return session.insert(namespace + ".MemberRegisterNaver", vo);
	}

}
