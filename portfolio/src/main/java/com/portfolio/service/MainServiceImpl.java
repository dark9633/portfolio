package com.portfolio.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.persistence.MainDAO;


@Service
public class MainServiceImpl implements MainService{

	@Inject private MainDAO dao;
	
	@Override
	public Date SampleSelectDate() throws Exception {
		return dao.SampleSelectDate();
	}
	
}
