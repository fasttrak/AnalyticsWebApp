package com.webapp.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.bo.UserBO;
import com.webapp.dto.PaginationDTO;
import com.webapp.entity.User;
import com.webapp.util.WebAppUtil;

@EnableAutoConfiguration
@RestController
@ComponentScan
@RequestMapping("/webapp/user")
@ImportResource("classpath:Beans.xml")
public class UserController {
	
	@Autowired
	UserBO userBO;
	
	@ResponseBody
	@RequestMapping("/login")
	private ResponseEntity validateLogin(@Valid @RequestBody User user, HttpServletResponse response) {
		ResponseEntity responseEntity = userBO.validateLogin(user);
		if(responseEntity.getStatusCode() == HttpStatus.OK){
			Cookie sessionCookie=new Cookie("sessionid", Integer.toString(user.getSessionId()));
			sessionCookie.setPath("/webapp");
			response.addCookie(sessionCookie);
			
			Cookie usernameCookie=new Cookie("username", user.getUsername());
			usernameCookie.setPath("/webapp");
			response.addCookie(usernameCookie);
			
			Cookie idCookie=new Cookie("id", user.getId());
			idCookie.setPath("/webapp");
			response.addCookie(idCookie);
			
			Cookie roleCookie=new Cookie("role", user.getRole());
			roleCookie.setPath("/webapp");
			response.addCookie(roleCookie);
		}
		return responseEntity;
	}

	@ResponseBody
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	private ResponseEntity getUser(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		Map<String,String> coockieMap = WebAppUtil.getCookiesValue(cookies);
		String sessionid = coockieMap.get("sessionid");
		System.out.println("sessionid "+sessionid);
		ResponseEntity responseEntity = userBO.getUser(sessionid);
		return responseEntity;
	}

	@ResponseBody
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	private ResponseEntity updateUser(@RequestBody User user) {
		ResponseEntity responseEntity = userBO.updateUser(user);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private ResponseEntity addUser(@RequestBody User user) {
		ResponseEntity responseEntity = userBO.addUser(user);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	private ResponseEntity deleteUser(@RequestBody User user) {
		ResponseEntity responseEntity = userBO.deleteUser(user);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/changeUserPassword", method = RequestMethod.POST)
	private ResponseEntity changeUserPassword(@RequestBody User user, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		Map<String,String> coockieMap = WebAppUtil.getCookiesValue(cookies);
		String sessionid = coockieMap.get("sessionid");
		ResponseEntity responseEntity = userBO.changeUserPassword(user, sessionid);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	private boolean logout(HttpServletResponse response) {
		response.addCookie(new Cookie("sessionid", ""));
		response.addCookie(new Cookie("username", ""));
		response.addCookie(new Cookie("id", ""));
		response.addCookie(new Cookie("role", ""));
		return true;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.POST)
	private ResponseEntity getAllUsers(@RequestBody PaginationDTO paginationDTO) {
		ResponseEntity responseEntity = userBO.getAllUsers(paginationDTO);
		return responseEntity;
	}
}
