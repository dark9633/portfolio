package com.portfolio.admin.controller;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.domain.AdminVO;
import com.portfolio.service.EtcService;

/*
 * 사이트 관리자 컨트롤러
 * 기본적인 사이트 관리를 위한 부분만 추가한다
 * 
 * 1. 관리자 로그인 추가
 * 2. 관리자 로그아웃 추가
 * 3. 관리자 메인 페이지 추가
 * 4. 사이트 로그 페이지 추가
 * 
 * */
@Controller
@RequestMapping("/admin*")
public class AdminController {
	
	@Inject private EtcService eService;
	
	/* 관리자 메인 페이지 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) throws Exception {
		return "admin/index";
	}
	
	/* 관리자 로그인 페이지 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() throws Exception {
		return "admin/login";
	}
	
	/* 관리자 로그인 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(AdminVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		System.out.println("ID : " + vo.getId());
		System.out.println("PWD : " + vo.getPwd());
		session.setAttribute("admin", vo);
		return "redirect:/admin/";
	}
	
	/* 관리자 로그아웃 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		return "redirect:/admin/login";
	}
	
	/* 로그 리스트 페이지 */
	@RequestMapping(value = "/log/list", method = RequestMethod.GET)
	public String logList(Model model) throws Exception {
		return "admin/log/list";
	}
	
	/* 사이트 로그 데이터 */
	@RequestMapping(value = "/log/log", method = RequestMethod.GET)
	public ResponseEntity<List<Map<?, ?>>> pygmyLog(HttpServletRequest request){
		try {
			return new ResponseEntity<List<Map<?, ?>>>(eService.LogListLog(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<?, ?>>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/* 사이트 브라우저 로그 데이터 */
	@RequestMapping(value = "/log/browser", method = RequestMethod.GET)
	public ResponseEntity<List<Map<?, ?>>> pygmyBrowser(HttpServletRequest request){
		try {
			return new ResponseEntity<List<Map<?, ?>>>(eService.LogListBrowser(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<?, ?>>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/* 사이트 디바이스 로그 데이터 */
	@RequestMapping(value = "/log/device", method = RequestMethod.GET)
	public ResponseEntity<List<Map<?, ?>>> pygmyBrowserTable(HttpServletRequest request){
		try {
			return new ResponseEntity<List<Map<?, ?>>>(eService.LogListDevice(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<?, ?>>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/* 사이트 주소 로그 데이터 */
	@RequestMapping(value = "/log/url", method = RequestMethod.GET)
	public ResponseEntity<List<Map<?, ?>>> pygmyUrl(HttpServletRequest request){
		try {
			return new ResponseEntity<List<Map<?, ?>>>(eService.LogListUrl(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<?, ?>>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
