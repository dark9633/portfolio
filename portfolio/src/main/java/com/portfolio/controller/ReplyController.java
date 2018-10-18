package com.portfolio.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/* 댓글 리스트 */
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
	
	/* 댓글 등록 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		
		try {
			int succ = service.ReplyRegister(vo);
			if(succ > 0){
				entity = new ResponseEntity<String>("succ", HttpStatus.OK);
			}else{
				entity = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/* 댓글 삭제 */
	@RequestMapping(value = "/{reNumber}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("reNumber") Integer reNumber){
		ResponseEntity<String> entity = null;
		
		try {
			int succ = service.ReplyDelete(reNumber);
			if(succ > 0){
				entity = new ResponseEntity<String>("succ", HttpStatus.OK);
			}else{
				entity = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/* 댓글 수정*/
	@RequestMapping(value = "", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		
		try {
			int succ = service.ReplyUpdate(vo);
			if(succ > 0){
				entity = new ResponseEntity<String>("succ", HttpStatus.OK);
			}else{
				entity = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
