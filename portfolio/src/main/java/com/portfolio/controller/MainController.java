package com.portfolio.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.domain.LogVO;
import com.portfolio.domain.MemberVO;
import com.portfolio.domain.SkillsVO;
import com.portfolio.service.BoardService;
import com.portfolio.service.EtcService;
import com.portfolio.service.PortfolioService;
import com.portfolio.service.SkillsService;
import com.portfolio.utils.DeviceCheck;

/*
 * 기본 컨트롤러
 * 메인에서부터 기본이 되는 페이지를 컨트롤 한다.
 * */
@Controller
public class MainController {
	
	//private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	//@Inject private MainService service;
	@Inject private BoardService bService;
	@Inject private SkillsService sService;
	@Inject private PortfolioService pService;
	@Inject private EtcService eService;
	
	/* 메인 인덱스 페이지 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) throws Exception {
		List<SkillsVO> skills = sService.SkillsListGroupNew();
		List<Map<String, Object>> array = new ArrayList<Map<String,Object>>();
		for (SkillsVO skill : skills) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nickName", skill.getNickName());
			map.put("category", skill.getCategory());
			map.put("regDate", skill.getRegDate());
			map.put("skills", sService.SkillsSubCategoryList(map));
			array.add(map);
		}
		
		model.addAttribute("skillsList", array);
		model.addAttribute("portfolioList", pService.PortfolioListNew());
		model.addAttribute("boardList", bService.BoardListNew());
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
	
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ResponseEntity<String> facebookReportImageUpload(HttpServletRequest request) throws Exception{
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null){ip = request.getRemoteAddr();}
		Integer memNumber = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("member") != null){
			MemberVO member = (MemberVO) session.getAttribute("member");
			memNumber = member.getMemNumber();
		}
		
		if(
				!ip.equals("localhost") &&
				!ip.equals("127.0.0.1") &&
				!ip.equals("59.0.252.164") &&
				!ip.equals("211.223.2.197")
			){
			String user_agent = request.getHeader("user-agent");
			String device = DeviceCheck.deviceCheck(user_agent);
			String referer = request.getHeader("referer");
			String url = "";
			if(referer != null){ url = referer; }
			
			LogVO vo = new LogVO();
			vo.setIp(ip);
			vo.setDevice(device);
			vo.setUserAgent(user_agent);
			vo.setMemNumber(memNumber);
			vo.setUrl(url);
			
			if(device.indexOf("web browser") != -1){
				vo.setBrowser("웹 브라우저");
			}else if(device.indexOf("mobile") != -1){
				vo.setBrowser("모바일 브라우저");
			}else{
				vo.setBrowser("미확인 브라우저");
			}
			
			eService.LogRegister(vo);
		}
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
}
