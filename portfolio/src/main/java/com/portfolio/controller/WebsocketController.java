package com.portfolio.controller;


import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.domain.ChatVO;
import com.portfolio.domain.MemberVO;
import com.portfolio.service.EtcService;


/*
 * 웹 소켓 컨트롤러
 * 아파치를 이용하는 경우 리눅스에서 문제가 될 수 있다. 관련 이슈 추가 예정(http://portfolio.freeserver.zone/)
 * */
@Controller
@RequestMapping("/ws*")
public class WebsocketController {
	
	//private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject private EtcService service;
	
	@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResponseEntity<String> location(@RequestBody ChatVO vo, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		JSONObject json = new JSONObject();
		
		if(member == null){
			json.put("result", "login");
			return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		}
		
		vo.setNickName(member.getNickName());
		service.ChattingRegister(vo);
		
		json.put("result", "succ");
		json.put("nickName", URLEncoder.encode(member.getNickName(), "UTF-8"));
		json.put("content", URLEncoder.encode(vo.getContent(), "UTF-8"));
		
		return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<ChatVO>> list(HttpServletRequest request) throws Exception{
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null){ip = request.getRemoteAddr();}
		return new ResponseEntity<List<ChatVO>>(service.ChattingList(), HttpStatus.OK);
	}
	
}
