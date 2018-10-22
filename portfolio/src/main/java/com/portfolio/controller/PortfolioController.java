package com.portfolio.controller;



import java.io.File;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.portfolio.domain.PortfolioVO;
import com.portfolio.service.PortfolioService;
import com.portfolio.utils.UploadFileUtils;

/*
 * 포트폴리오 컨트롤러
 * */
@Controller
@RequestMapping("/portfolio*")
public class PortfolioController {
	
	//private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);
	
	@Resource(name = "uploadPath") private String uploadPath;
	
	@Inject private PortfolioService service;
	
	/* 포트폴리오 리스트 페이지 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String list(Criteria cri, Model model) throws Exception {
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.PortfolioListCount(cri));
		
		model.addAttribute("portfolio", service.PortfolioList(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return "portfolio/portfolio_list";
	}
	
	/* 포트폴리오 상세 페이지 */
	@RequestMapping(value = "/view/{pfNumber}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String view(@PathVariable("pfNumber") Integer pfNumber, Model model) throws Exception {
		
		return "portfolio/portfolio_view";
	}
	
	/* 포트폴리오 등록 페이지 */
	@RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String register(Model model) throws Exception {
		return "portfolio/portfolio_register";
	}
	
	/* 포트폴리오 등록 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(PortfolioVO vo, MultipartFile file, HttpServletRequest request, RedirectAttributes attr) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("img");
		vo.setNickName(((MemberVO)session.getAttribute("member")).getNickName());
		
		String image = UploadFileUtils.noneChangeNameUploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		vo.setImage(image);
		
		int succ = service.PortfolioRegister(vo);
		if(succ > 0){
			attr.addFlashAttribute("result", "succ");
		}else{
			attr.addFlashAttribute("result", "fail");
		}
		return "redirect:/portfolio/list/";
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
