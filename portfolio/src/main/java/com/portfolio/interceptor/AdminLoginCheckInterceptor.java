package com.portfolio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.portfolio.domain.AdminVO;


public class AdminLoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		AdminVO admin = (AdminVO) session.getAttribute("admin");
		if(admin == null){
			response.sendRedirect("/admin/login");
		}
		return super.preHandle(request, response, handler);
	}

}
