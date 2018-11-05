package com.portfolio.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.utils.Utils;


/*
 * 다음 컨트롤러
 * 지도 API 추가
 * 지도 API 사용법에 관해서는 기술개발쪽에 추가 할 예정(http://portfolio.freeserver.zone/)
 * */
@Controller
@RequestMapping("/daum*")
public class DaumController {
	
	//private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@ResponseBody
	@RequestMapping(value = "/location/{address}", method = RequestMethod.GET)
	public ResponseEntity<String> location(@PathVariable("address") String address){
		try {
			return new ResponseEntity<String>(Utils.getLocation(address).toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
