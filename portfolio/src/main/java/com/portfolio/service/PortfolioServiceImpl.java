package com.portfolio.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.persistence.PortfolioDAO;


@Service
public class PortfolioServiceImpl implements PortfolioService{

	@Inject private PortfolioDAO dao;
	
}
