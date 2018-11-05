package com.portfolio.persistence;

import com.portfolio.domain.MemberVO;


public interface MemberDAO {

	/* 이미 가입한 메일인지 확인 */
	public int MemberEmailExistCheck(MemberVO vo) throws Exception;
	
	/* 닉네임 중복검사 */
	public int MemberNickNameExistCheck(MemberVO vo) throws Exception;
	
	/* 회원 가입 */
	public int MemberRegister(MemberVO vo) throws Exception;
	
	/* 회원 로그인 */
	public MemberVO MemberLogin(MemberVO vo) throws Exception;
	
	/* 회원 로그인 네이버 */
	public MemberVO MemberLoginNaver(MemberVO vo) throws Exception;
	
	/* 회원 가입 네이버 */
	public int MemberRegisterNaver(MemberVO vo) throws Exception;
	
}
