package com.portfolio.controller;


import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.domain.Criteria;
import com.portfolio.domain.MemberVO;
import com.portfolio.domain.PageMaker;
import com.portfolio.domain.SkillsVO;
import com.portfolio.service.SkillsService;
import com.portfolio.utils.UploadFileUtils;

/*
 * 스킬 컨트롤러
 * 2018/10/22 마지막으로 스킬 기능 추가 마무리.
 * 추가기능이 필요할 경우 하단에 기재 업데이트 후 삭제 필요
 * 
 * 스킬리스트 통합
 * 검색기능 추가
 * 
 * 2. 회원 단위 리스트 추가
 * 3. 이미지 변경 삭제 발생시 실제 이미지 삭제 추가
 * 4. 상세한 검증 추가
 * */
@Controller
@RequestMapping("/skills*")
public class SkillsController {
	
	//private static final Logger logger = LoggerFactory.getLogger(SkillsController.class);
	@Resource(name = "uploadPath") private String uploadPath;
	
	@Inject SkillsService service;
	
	/* 스킬 리스트 전체 페이지 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String list(Model model, Criteria cri) throws Exception {
		/* 영역을 4개로 잡아서 4의 배수로 수정필요 */
		cri.setPerPageNum(12);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.SkillsListGroupCount(cri));
		
		List<SkillsVO> skills = service.SkillsListGroup(cri);
		List<Map<String, Object>> array = new ArrayList<Map<String,Object>>();
		for (SkillsVO skill : skills) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nickName", skill.getNickName());
			map.put("category", skill.getCategory());
			map.put("regDate", skill.getRegDate());
			map.put("skills", service.SkillsSubCategoryList(map));
			array.add(map);
		}
		
		model.addAttribute("list", array);
		model.addAttribute("pageMaker", pageMaker);
		
		return "skills/skills_list";
	}
	
	/* 
	 * 스킬 리스트 카테고리 페이지 
	 * 카테고리를 cri에 담아서 통합 사용
	 * */
	/*@RequestMapping(value = "/list/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String listCategory(@PathVariable("category") String category, Model model, Criteria cri) throws Exception {
		cri.setCategory(category);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.SkillsListGroupCount(cri));
		
		List<SkillsVO> skills = service.SkillsListGroup(cri);
		List<Map<String, Object>> array = new ArrayList<Map<String,Object>>();
		for (SkillsVO skill : skills) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nickName", skill.getNickName());
			map.put("category", skill.getCategory());
			map.put("regDate", skill.getRegDate());
			map.put("skills", service.SkillsSubCategoryList(map));
			array.add(map);
		}
		
		model.addAttribute("list", array);
		model.addAttribute("pageMaker", pageMaker);
		return "skills/skills_list";
	}*/
	
	/* 스킬 리스트 회원 단위 카테고리 페이지 */
	@RequestMapping(value = "/list/{nickName}/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String listNickNameAndCategory(
			@PathVariable("nickName") String nickName, @PathVariable("category") String category, Model model,
			Criteria cri, HttpServletRequest request
				) throws Exception {
		cri.setCategory(category);
		cri.setNickName(nickName);
		cri.setSubCategory(request.getParameter("subCategory"));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.SkillsListCount(cri));
		
		model.addAttribute("subCategory", service.SkillsSubCategoryListAll(cri));
		model.addAttribute("skills", service.SkillsList(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return "skills/skills_category_list";
	}
	
	/* 스킬 상세 페이지 */
	@RequestMapping(value = "/view/{skNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String view(@PathVariable("skNumber") Integer skNumber, Model model, @ModelAttribute("cri") Criteria cri) throws Exception {
		SkillsVO skills = service.SkillsView(skNumber);
		model.addAttribute("view", skills);
		String description = skills.getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		if(description.length() >= 100){
			description = description.substring(0, 99);
		}
		model.addAttribute("description", description);
		return "skills/skills_view";
	}
	
	/* 스킬 등록 페이지 */
	@RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String register(Model model) throws Exception {
		return "skills/skills_register";
	}
	
	/* 스킬 등록 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(SkillsVO vo, HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("img");
		vo.setNickName(((MemberVO)session.getAttribute("member")).getNickName());
		
		int succ = service.SkillsRegister(vo);
		if(succ > 0){
			attr.addFlashAttribute("result", "succ");
		}else{
			attr.addFlashAttribute("result", "fail");
		}
		return "redirect:/skills/list/" + URLEncoder.encode(vo.getNickName(), "UTF-8") +"/" + vo.getCategory()+"?subCategory=" + URLEncoder.encode(vo.getSubCategory(), "UTF-8");
	}
	
	/* 스킬 수정 페이지 */
	@RequestMapping(value = "/modify/{skNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String modify(@PathVariable("skNumber") int skNumber, Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		/* 비로그인시 메인 페이지로 */
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member == null) {
			return "redirect:/";
		}

		SkillsVO skills = service.SkillsView(skNumber);
		
		/* 작성자와 게시물의 닉네임이 다른경우 메인으로 */
		if(!skills.getNickName().equals(member.getNickName())) {
			return "redirect:/";
		}
		
		model.addAttribute("view", skills);
		
		return "skills/skills_modify";
	}
	
	/* 스킬 수정 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(SkillsVO vo, HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("img");
		
		/* 비로그인시 메인 페이지로 */
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member == null) {
			return "redirect:/";
		}

		/* 작성자와 게시물의 닉네임이 다른경우 메인으로 */
		if(!vo.getNickName().equals(member.getNickName())) {
			return "redirect:/";
		}
		
		int succ = service.SkillsModify(vo);
		if(succ > 0){
			attr.addFlashAttribute("result", "succ");
		}else{
			attr.addFlashAttribute("result", "fail");
		}
		return "redirect:/skills/view/" + vo.getSkNumber();
	}
	
	/* 스킬 삭제 */
	@RequestMapping(value = "/delete/{skNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String delete(@PathVariable("skNumber") int skNumber, RedirectAttributes attr, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		/* 비로그인시 메인 페이지로 */
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member == null) {
			return "redirect:/";
		}

		SkillsVO skills = service.SkillsView(skNumber);
		
		/* 작성자와 게시물의 닉네임이 다른경우 메인으로 */ 
		if(!skills.getNickName().equals(member.getNickName())) {
			return "redirect:/";
		}
		
		int succ = service.SkillsDelete(skNumber);
		if(succ > 0){
			attr.addFlashAttribute("result", "succ");
		}else{
			attr.addFlashAttribute("result", "fail");
		}
		return "redirect:/skills/list";
	}
	
	/*
	 * 멀티 이미지 업로드
	 * 설정상 C:\home\img\portfolio폴더가 존재해야 한다.
	 * 추후 개별 관리를 위해 모든 이미지 업로드는 컨트롤러 별로 존재한다.
	 * */
	@ResponseBody
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> uploadAjaxMultiple(HttpServletRequest request,MultipartRequest mr) throws Exception{
		
		HttpSession session = request.getSession();
		Iterator<String> fileN = mr.getFileNames();
		String result = "";
		String name = "";
		while(fileN.hasNext()){
			MultipartFile file = mr.getFile(fileN.next());
			name = UploadFileUtils.defaultUploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());

			String imgname = (String) session.getAttribute("img");
			
			if(imgname == null){
				imgname = "";
			}
			
			imgname = imgname +","+ name;
			session.setAttribute("img", imgname);
			
			result += name + "123456789----------";
		}
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}
	
	/* 이미지삭제 */
	@RequestMapping(value="/imageDelete", method = RequestMethod.GET)
	public ResponseEntity<String> imageDelete(HttpServletRequest request)throws Exception{
		ResponseEntity<String> entity = new ResponseEntity<String>("succ", HttpStatus.OK);
		
		HttpSession session = request.getSession();
		String imgname = (String) session.getAttribute("img");
		if(imgname != null){
			String[] imgs = imgname.split(",");
			for (String string : imgs) {
				File file = new File(uploadPath + string);
				if(file.exists()){
					if(file.delete()){
						System.out.println("session file delete");
					}
				}
			}
			session.removeAttribute("img");
		}
		return entity;
	}
	
}
