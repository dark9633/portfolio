package com.portfolio.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.persistence.ReplyDAO;


@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject private ReplyDAO dao;
	
}
