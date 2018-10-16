package com.portfolio.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * 스킬 컨트롤러
 * */
@Controller
@RequestMapping("/skills*")
public class SkillsController {
	
	private static final Logger logger = LoggerFactory.getLogger(SkillsController.class);
	
	/* 스킬 리스트 페이지 */
	@RequestMapping(value = "/list/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String list(@PathVariable("category") String category, Model model) throws Exception {
		
		return "skills/skills_list";
	}
	
	/* 스킬 상세 페이지 */
	@RequestMapping(value = "/view/{skNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String view(@PathVariable("skNumber") Integer pfNumber, Model model) throws Exception {
		
		return "skills/skills_view";
	}
	
}
