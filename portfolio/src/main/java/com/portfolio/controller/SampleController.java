package com.portfolio.controller;


import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * 테스트용 컨트롤러
 * */
@Controller
public class SampleController {
	
	@RequestMapping(value = "/res01_test", method = RequestMethod.GET)
	public String res01_test(Locale locale, Model model) throws Exception {
		return "sample/res01_test";
	}
}
