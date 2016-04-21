package com.webapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.bo.AnalyticBO;

@EnableAutoConfiguration
@RestController
@ComponentScan
@RequestMapping("/webapp/analytics")
@ImportResource("classpath:Beans.xml")
public class AnalyticController {

	@Autowired
	AnalyticBO analyticBO;

	@ResponseBody
	@RequestMapping(value = "/getAllAnalytics", method = RequestMethod.GET)
	private ResponseEntity getAllAnalytics() {
		return analyticBO.getAllAnalytics();
	}
	
}
