package com.portfolio.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.domain.Criteria;

/*
 * 게시판 컨트롤러
 * */
@Controller
@RequestMapping("/board*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/* 게시판 리스트 페이지 */
	@RequestMapping(value = "/list/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String list(@PathVariable("category") String category, Model model) throws Exception {
		
		return "board/board_list";
	}
	
	/* 게시판 상세 페이지 */
	@RequestMapping(value = "/view/{bNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String view(@PathVariable("bNumber") Integer bNumber, Model model) throws Exception {
		
		return "board/board_view";
	}
	
	/* 게시판 등록 페이지 */
	@RequestMapping(value = "/register/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String register(@PathVariable("category") String category, Model model) throws Exception {
		Criteria cri = new Criteria();
		cri.setCategory(category);
		model.addAttribute("cri", cri);
		return "board/board_register";
	}
	
}
