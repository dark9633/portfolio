package com.portfolio.controller;


import java.io.File;
import java.net.URLEncoder;
import java.util.Iterator;

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

import com.portfolio.domain.BoardVO;
import com.portfolio.domain.Criteria;
import com.portfolio.domain.MemberVO;
import com.portfolio.domain.PageMaker;
import com.portfolio.service.BoardService;
import com.portfolio.utils.UploadFileUtils;

/*
 * 게시판 컨트롤러
 * 2018/10/20 마지막으로 게시판 기능 추가 마무리, 추후 필요 기능이 생길 경우 하단에 기재
 * 
 * 1. 검색기능 추가
 * */
@Controller
@RequestMapping("/board*")
public class BoardController {
	
	//private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "uploadPath") private String uploadPath;
	
	@Inject private BoardService bService;
	
	/* 게시판 리스트 페이지 */
	@RequestMapping(value = "/list/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String list(@PathVariable("category") String category, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		cri.setCategory(category);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bService.BoardListCount(cri));
		
		model.addAttribute("board", bService.BoardList(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/board_list";
	}
	
	/* 게시판 상세 페이지 */
	@RequestMapping(value = "/view/{bNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String view(@PathVariable("bNumber") Integer bNumber, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		model.addAttribute("view", bService.BoardView(bNumber));
		return "board/board_view";
	}
	
	/* 게시글 등록 페이지 */
	@RequestMapping(value = "/register/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String register(@PathVariable("category") String category, Model model) throws Exception {
		Criteria cri = new Criteria();
		cri.setCategory(category);
		model.addAttribute("cri", cri);
		return "board/board_register";
	}
	
	/* 게시글 등록 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(BoardVO vo, HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("img");
		vo.setNickName(((MemberVO) session.getAttribute("member")).getNickName());
		int succ = bService.BoardRegister(vo);
		if(succ > 0){
			attr.addFlashAttribute("result", "succ");
		}else{
			attr.addFlashAttribute("result", "fail");
		}
		return "redirect:/board/list/" + URLEncoder.encode(vo.getCategory(), "UTF-8");
	}
	
	/* 게시글 수정 페이지 */
	@RequestMapping(value = "/modify/{category}/{bNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String modify(@PathVariable("category") String category, @PathVariable("bNumber") int bNumber, Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		/* 비로그인시 메인 페이지로 */
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member == null) {
			return "redirect:/";
		}

		BoardVO board = bService.BoardView(bNumber);
		
		/* 작성자와 게시물의 닉네임이 다른경우 메인으로 */
		if(!board.getNickName().equals(member.getNickName())) {
			return "redirect:/";
		}
		
		Criteria cri = new Criteria();
		cri.setCategory(category);
		
		model.addAttribute("cri", cri);
		model.addAttribute("view", bService.BoardView(bNumber));
		
		return "board/board_modify";
	}
	
	/* 게시글 수정 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(BoardVO vo, HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
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
		
		int succ = bService.BoardModify(vo);
		if(succ > 0){
			attr.addFlashAttribute("result", "succ");
		}else{
			attr.addFlashAttribute("result", "fail");
		}
		return "redirect:/board/view/" + vo.getbNumber();
	}
	
	/* 게시글 삭제 */
	@RequestMapping(value = "/delete/{category}/{bNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String delete(@PathVariable("category") String category, @PathVariable("bNumber") int bNumber, RedirectAttributes attr, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		/* 비로그인시 메인 페이지로 */
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member == null) {
			return "redirect:/";
		}

		BoardVO board = bService.BoardView(bNumber);
		
		/* 작성자와 게시물의 닉네임이 다른경우 메인으로 */
		if(!board.getNickName().equals(member.getNickName())) {
			return "redirect:/";
		}
		
		int succ = bService.BoardDelete(bNumber);
		if(succ > 0){
			attr.addFlashAttribute("result", "succ");
		}else{
			attr.addFlashAttribute("result", "fail");
		}
		return "redirect:/board/list/" + URLEncoder.encode(category, "UTF-8");
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
