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
 * 네이버 로그인 컨트롤러
 * 네아로 설정은 추후 기술개발쪽에 추가 할 예정(http://portfolio.freeserver.zone/)
 * 필수적으로 닉네임과 이메일을 받고 있음
 * 로그인모달에서 회원가입 및 로그인까지 처리한 상태 <- 이건 취향에 따라 회원가입에 버튼 추가하고 네아로 설정 수정해야함
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
