package com.webapp.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.dao.UserDAO;
import com.webapp.entity.User;
import com.webapp.util.WebAppUtil;


@Component
public class SessionValidatorInterceptor implements HandlerInterceptor{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		//retrieve cookie here
		boolean loggedIn = false;
		Cookie[] cookies = req.getCookies();
		if(cookies != null){
			Map<String,String> coockieMap = WebAppUtil.getCookiesValue(cookies);
			String username = coockieMap.get("username");
			String sessionid = coockieMap.get("sessionid");
			System.out.println("username "+username);
			System.out.println("sessionid "+sessionid);
			if(username == null || "".equals(username.trim()) || sessionid == null || "".equals(sessionid.trim())){
				res.sendError(400, "invalid session");
				return false;
			}
			User user=userDAO.getUserFromSessionId(Integer.parseInt(sessionid));
			if(user==null || !username.equals(user.getUsername())){
				res.sendError(400, "invalid session");
				return false;
			}
			loggedIn = true;
			System.out.println("loggedin "+loggedIn);
		}
		return loggedIn;
	}

}