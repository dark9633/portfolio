package com.portfolio.service;

import com.portfolio.domain.MemberVO;


public interface MemberService {

	/* 이미 가입한 메일인지 확인 */
	public int MemberEmailExistCheck(MemberVO vo) throws Exception;
	
	/* 닉네임 중복검사 */
	public int MemberNickNameExistCheck(MemberVO vo) throws Exception;
	
	/* 회원 가입 */
	public int MemberRegister(MemberVO vo) throws Exception;
	
}
