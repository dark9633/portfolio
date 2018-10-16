package com.portfolio.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.persistence.SkillsDAO;


@Service
public class SkillsServiceImpl implements SkillsService{

	@Inject private SkillsDAO dao;
	
}
