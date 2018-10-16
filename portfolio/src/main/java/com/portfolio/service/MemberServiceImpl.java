package com.portfolio.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.persistence.MemberDAO;


@Service
public class MemberServiceImpl implements MemberService{

	@Inject private MemberDAO dao;
	
}
