package com.portfolio.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.domain.MemberVO;
import com.portfolio.persistence.MemberDAO;


@Service
public class MemberServiceImpl implements MemberService{

	@Inject private MemberDAO dao;

	@Override
	public int MemberEmailExistCheck(MemberVO vo) throws Exception {
		return dao.MemberEmailExistCheck(vo);
	}

	@Override
	public int MemberNickNameExistCheck(MemberVO vo) throws Exception {
		return dao.MemberNickNameExistCheck(vo);
	}

	@Override
	public int MemberRegister(MemberVO vo) throws Exception {
		return dao.MemberRegister(vo);
	}

	@Override
	public MemberVO MemberLogin(MemberVO vo) throws Exception {
		return dao.MemberLogin(vo);
	}

	@Override
	public MemberVO MemberLoginNaver(MemberVO vo) throws Exception {
		return dao.MemberLoginNaver(vo);
	}

	@Override
	public int MemberRegisterNaver(MemberVO vo) throws Exception {
		return dao.MemberRegisterNaver(vo);
	}
	
}
