package com.portfolio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LogoutCheckInterceptor extends HandlerInterceptorAdapter {

	//private static final Logger logger = LoggerFactory.getLogger(LogoutCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("member") != null){
			response.sendRedirect("/");
		}
		return super.preHandle(request, response, handler);
	}

}
