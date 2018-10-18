package com.portfolio.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.domain.MemberVO;
import com.portfolio.service.MemberService;
import com.portfolio.utils.CodeCreate;
import com.portfolio.utils.MailSendThred;
import com.portfolio.utils.PasswordSecurity;


/*
 * 회원 컨트롤러
 * */
@Controller
@RequestMapping("/member*")
public class MemberController {
	
	//private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject private MemberService service;
	
	/* 회원가입 페이지 */
	@RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String register(Model model) throws Exception {
		return "member/member_register";
	}
	
	/* 회원가입  */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(MemberVO vo, Model model, RedirectAttributes rttr) throws Exception {
		
		//비밀번호는 암호화 처리
		vo.setPwd(new PasswordSecurity().encryptSHA256(vo.getPwd()));
		
		int succ = service.MemberRegister(vo);
		if(succ > 0){
			rttr.addFlashAttribute("result", "succ");
		}else{
			rttr.addFlashAttribute("result", "fail");
		}
		return "redirect:/";
	}
	
	/* 
	 * 메일 검증 컨트롤러 
	 * 코드를 메일로 전송하고 session에 코드를 임시적으로 저장한다.
	 * */
	@ResponseBody
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	public ResponseEntity<String> memberMailCheck(String email, HttpServletRequest request){

		ResponseEntity<String> entity = null;
		MemberVO vo = new MemberVO();
		vo.setEmail(email);
		
		try {
			int check = service.MemberEmailExistCheck(vo);
			if(check == 0){
				String code = CodeCreate.getCodeCreate();
				HttpSession session = request.getSession();
				session.setAttribute("code", code);
				session.setMaxInactiveInterval(60*5);
				
				String html = "<br><h3>아래 코드를 사이트에서 입력하시고 확인해주세요.</h3>";
				html += "<br>"+code+"<br>";
				
				MailSendThred.MailSend(email, "회원가입 코드입니다.", html);
				entity = new ResponseEntity<String>("succ", HttpStatus.OK);
			}else{
				entity = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/* 메일로 전송한 코드를 검증*/
	@ResponseBody
	@RequestMapping(value = "/codeCheck", method = RequestMethod.GET)
	public ResponseEntity<String> memberCodeCheck(String code, HttpServletRequest request){

		HttpSession session = request.getSession();
		String sessionCode = (String) session.getAttribute("code");
		if(sessionCode != null && code != null){
			if(sessionCode.equals(code)){
				session.invalidate();
				return new ResponseEntity<String>("succ", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
	
	/* 닉네임 중복 검증 */
	@ResponseBody
	@RequestMapping(value = "/nickCheck", method = RequestMethod.GET)
	public ResponseEntity<String> memberNickCheck(String nickName, HttpServletRequest request){
		
		MemberVO vo = new MemberVO();
		vo.setNickName(nickName);
		
		try {
			int check = service.MemberNickNameExistCheck(vo);
			if(check == 0){
				return new ResponseEntity<String>("succ", HttpStatus.OK);
			}else{
				return new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
	
}
