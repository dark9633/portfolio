package com.portfolio.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * 서버단이 아닌 웹단에서 에러코드를 컨트롤한다.
 * 에러 코드별 로그를 남긴후 에러페이지를 호출한다.
 * web.xml
 * ErrorController.java
 * */

@Controller
@RequestMapping(value = "/error*")
public class ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@RequestMapping(value = "/400", method = RequestMethod.GET)
	public String code400(Model model, HttpServletRequest request) throws Exception {
		logger.warn("error log 400 code");
		return "error/error-common";
	}
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String code403(Model model, HttpServletRequest request) throws Exception {
		logger.warn("error log 403 code");
		return "error/error-common";
	}
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String code404(Model model, HttpServletRequest request) throws Exception {
		logger.warn("error log 404 code");
		return "error/error-common";
	}
	@RequestMapping(value = "/405", method = RequestMethod.GET)
	public String code405(Model model, HttpServletRequest request) throws Exception {
		logger.warn("error log 405 code");
		return "error/error-common";
	}
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public String code500(Model model, HttpServletRequest request) throws Exception {
		logger.warn("error log 500 code");
		return "error/error-common";
	}
	@RequestMapping(value = "/503", method = RequestMethod.GET)
	public String code503(Model model, HttpServletRequest request) throws Exception {
		logger.warn("error log 503 code");
		return "error/error-common";
	}
	
}
