package com.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.webapp.interceptor.SessionValidatorInterceptor;


@RestController
@RequestMapping("/webapp/*")
public class WebAppController extends WebMvcConfigurerAdapter{

	@Autowired
	SessionValidatorInterceptor sessionValidatorInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionValidatorInterceptor).addPathPatterns("/webapp/user/getUser");
	}
	
}
