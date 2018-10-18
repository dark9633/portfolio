package com.portfolio.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.domain.ReplyVO;
import com.portfolio.service.ReplyService;

/*
 * 댓글 컨트롤러
 * */
@Controller
@RequestMapping("/reply*")
public class ReplyController {
	
	//private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Inject private ReplyService service;
	
	@RequestMapping(value = "/list/{bNumber}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> listAll(@PathVariable("bNumber") Integer bNumber){
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<List<ReplyVO>>(service.ReplyList(bNumber), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
