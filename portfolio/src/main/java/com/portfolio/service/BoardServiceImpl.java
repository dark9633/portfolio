package com.portfolio.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.persistence.BoardDAO;


@Service
public class BoardServiceImpl implements BoardService{

	@Inject private BoardDAO dao;
	
}
