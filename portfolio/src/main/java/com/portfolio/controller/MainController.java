package com.portfolio.controller;

import java.io.InputStream;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.service.MainService;

/*
 * 기본 컨트롤러
 * 메인에서부터 기본이 되는 페이지를 컨트롤 한다.
 * */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Inject private MainService service;
	
	/* 메인 인덱스 페이지 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) throws Exception {
		return "index";
	}
	
	/* 포트폴리오 개인 정보 페이지 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Locale locale, Model model) throws Exception {
		return "info/info";
	}
	
	/* 사이트맵 제출을 위한 컨트롤러 */
	@RequestMapping(value = "sitemap.xml", method = RequestMethod.GET)
	public void robot(Model model, HttpServletResponse response, HttpServletRequest request) {
	    InputStream resourceAsStream = null;
	    try {
	        ClassLoader classLoader = getClass().getClassLoader();
	        resourceAsStream = classLoader.getResourceAsStream("sitemap.xml");
	        response.addHeader("Content-disposition", "filename=sitemap.xml");
	        response.setContentType("text/plain");
	        IOUtils.copy(resourceAsStream, response.getOutputStream());
	        response.flushBuffer();
	    } catch (Exception e) {
	    	System.out.println(e);
	    } 
	}
	
	/* robots.txt 설정 컨트롤러 */
	@RequestMapping(value = {"/robots", "/robot", "/robot.txt", "/robots.txt"}, method = RequestMethod.GET)
	public void sitemap(Model model, HttpServletResponse response, HttpServletRequest request) {
	    InputStream resourceAsStream = null;
	    try {
	        ClassLoader classLoader = getClass().getClassLoader();
	        resourceAsStream = classLoader.getResourceAsStream("robots.txt");
	        response.addHeader("Content-disposition", "filename=robots.txt");
	        response.setContentType("text/plain");
	        IOUtils.copy(resourceAsStream, response.getOutputStream());
	        response.flushBuffer();
	    } catch (Exception e) {
	    	System.out.println(e);
	    } 
	}
	
}
