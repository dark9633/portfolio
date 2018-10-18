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
	
}
