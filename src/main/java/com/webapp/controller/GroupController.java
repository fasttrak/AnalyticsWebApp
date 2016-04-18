package com.webapp.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.bo.GroupBO;
import com.webapp.dto.GroupDTO;
import com.webapp.entity.Group;
import com.webapp.entity.User;
import com.webapp.util.WebAppUtil;

@EnableAutoConfiguration
@RestController
@ComponentScan
@RequestMapping("/webapp/group")
@ImportResource("classpath:Beans.xml")
public class GroupController {

	@Autowired
	GroupBO groupBO;
	
	@ResponseBody
	@RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
	private ResponseEntity updateGroup(@RequestBody GroupDTO groupDTO) {
		ResponseEntity responseEntity = groupBO.updateGroup(groupDTO);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	private ResponseEntity addGroup(@RequestBody GroupDTO groupDTO) {
		ResponseEntity responseEntity = groupBO.addGroup(groupDTO);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	private ResponseEntity deleteGroup(@RequestBody Group group) {
		ResponseEntity responseEntity = groupBO.deleteGroup(group);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllGroups", method = RequestMethod.GET)
	private ResponseEntity getAllGroups() {
		ResponseEntity responseEntity = groupBO.getAllGroups();
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getGroup", method = RequestMethod.GET)
	private ResponseEntity getGroup(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		Map<String,String> coockieMap = WebAppUtil.getCookiesValue(cookies);
		String groupId = coockieMap.get("role");
		System.out.println("groupId "+groupId);
		ResponseEntity responseEntity = groupBO.getGroup(groupId);
		return responseEntity;
	}
	
}
