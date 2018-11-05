package com.portfolio.controller;


import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.domain.MemberVO;
import com.portfolio.service.MemberService;
import com.portfolio.utils.Utils;


/*
 * 네이버 로그인 컨트롤러
 * 네아로 설정은 추후 기술개발쪽에 추가 할 예정(http://portfolio.freeserver.zone/)
 * 필수적으로 닉네임과 이메일을 받고 있음
 * 로그인모달에서 회원가입 및 로그인까지 처리한 상태 <- 이건 취향에 따라 회원가입에 버튼 추가하고 네아로 설정 수정해야함
 * */
@Controller
@RequestMapping("/naver*")
public class NaverController {
	
	//테스트용 로컬 주소
	private final String navercallBack = "http://127.0.0.1:8080/naver/login";
	private final static String naverClientID = "{naverClientID}";
	private final static String naverClientSecret = "{naverClientSecret}";
	
	//private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject private MemberService service;
	
	//네이버 로그인 및 회원가입 토큰 경로
	@RequestMapping(value = "/token", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String naverBlogToken(HttpServletRequest request) throws Exception {
		String requestUrl = "https://nid.naver.com/oauth2.0/authorize?"+
							"client_id="+naverClientID+
							"&response_type=code"+
							"&redirect_uri="+navercallBack+
							"&state=";
		
		HttpSession session = request.getSession();
		
		String referer = request.getHeader("referer");
		String state = Utils.generateState();
		
		session.setAttribute("referer", referer);
		session.setAttribute("state", state);
		session.setMaxInactiveInterval(30*60*1);
		
		String rootPath = requestUrl+state;
		
		return "redirect:"+rootPath;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String naverBlogLogin(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		HttpSession session = request.getSession();
		
		String state = (String)request.getParameter("state");
		String code = (String)request.getParameter("code");
		String storedState = (String)session.getAttribute("state");
		
		String redirectUrl = "/";
		
		session.removeAttribute("state");
		session.removeAttribute("camNumber");
		
		//중간 변조 확인
		if(state.equals(storedState)){
			String data = Utils.getHtml(getAccessUrl(state, code), null);
			
			Map<String, String> map = Utils.JSONStringToMap(data);
			
			String accessToken = map.get("access_token");		//map에 담긴 access_token 값
			String tokenType = map.get("token_type");			//map에 담긴 token_type 값
			
			String profileDataXml = Utils.getHtml("https://openapi.naver.com/v1/nid/getUserProfile.xml", tokenType+" "+accessToken);
			
			JSONObject jsonObj = XML.toJSONObject(profileDataXml);
			jsonObj = jsonObj.getJSONObject("data");
			
			//로그인 성공여부 확인
			if(jsonObj.getJSONObject("result").getString("message").equals("success")){
				jsonObj = jsonObj.getJSONObject("response");
				
				MemberVO login = new MemberVO();
				login.setEmail(jsonObj.getString("email"));
				login.setNaverId(jsonObj.getString("id"));
				login.setNickName(jsonObj.getString("nickname"));
				
				MemberVO member = service.MemberLoginNaver(login);
				//회원 가입여부 확인 후 가입한 회원은 로그인 가입하지 않은 회원은 회원가입을 진행한다.
				if(member != null){
					session.setAttribute("member", member);
				}else{
					//닉네임 유니크 처리를 위한 코드
					String nickname = login.getNickName();
					int check = service.MemberNickNameExistCheck(login);
					while(check != 0){
						if(nickname.indexOf("(") != -1 && nickname.indexOf(")") != -1 && nickname.substring(nickname.length()-1).equals(")")){
							int cnt = Integer.parseInt(nickname.substring(nickname.indexOf("(")+1, nickname.indexOf(")"))) + 1;
							nickname = nickname.substring(0, nickname.indexOf("(")) + "(" + cnt + ")";
						}else{
							nickname = nickname + "(1)";
						}
						check = service.MemberNickNameExistCheck(login);
					}
					login.setNickName(nickname);
					
					service.MemberRegisterNaver(login);
					session.setAttribute("member", service.MemberLoginNaver(login));
				}
				
				if(session.getAttribute("referer") != null){
					redirectUrl = (String) session.getAttribute("referer");
					session.removeAttribute("referer");
				}
			}else{
				System.out.println("result message fail");
				redirectUrl = "/";
			}
		}else{
			System.out.println("state : " + state + ", storedState : " + storedState);
			redirectUrl = "/";
		}
		return "redirect:"+redirectUrl;
	}
	
	public static String getAccessUrl(String state, String code) {
		String accessUrlMain = "https://nid.naver.com/oauth2.0/token?"
				+ "client_id="+naverClientID
				+ "&client_secret="+naverClientSecret
				+ "&grant_type=authorization_code" 
				+ "&state=" + state
				+ "&code=" + code;
		return accessUrlMain;
	}
	
}
