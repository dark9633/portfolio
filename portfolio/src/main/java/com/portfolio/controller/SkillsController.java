package com.portfolio.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.domain.Criteria;
import com.portfolio.domain.PageMaker;

/*
 * 스킬 컨트롤러
 * */
@Controller
@RequestMapping("/skills*")
public class SkillsController {
	
	//private static final Logger logger = LoggerFactory.getLogger(SkillsController.class);
	
	/* 스킬 리스트 전체 페이지 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String list(Model model) throws Exception {
		return "skills/skills_list";
	}
	
	/* 스킬 리스트 카테고리 페이지 */
	@RequestMapping(value = "/list/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String listCategory(@PathVariable("category") String category, Model model) throws Exception {
		return "skills/skills_list";
	}
	
	/* 스킬 리스트 회원 페이지 */
	@RequestMapping(value = "/list/{nickName}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String listNickName(@PathVariable("nickName") String nickName, Model model) throws Exception {
		return "skills/skills_list";
	}
	
	/* 스킬 리스트 회원 단위 카테고리 페이지 */
	@RequestMapping(value = "/list/{nickName}/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String listNickNameAndCategory(
			@PathVariable("nickName") String nickName, @PathVariable("category") String category, Model model,
			Criteria cri
				) throws Exception {
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(0);
		
		model.addAttribute("skills", null);
		model.addAttribute("pageMaker", pageMaker);
		
		return "skills/skills_category_list";
	}
	
	/* 스킬 상세 페이지 */
	@RequestMapping(value = "/view/{skNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String view(@PathVariable("skNumber") Integer pfNumber, Model model) throws Exception {
		
		return "skills/skills_view";
	}
	
}
