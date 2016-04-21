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

import com.webapp.bo.AlertBO;
import com.webapp.bo.UserBO;
import com.webapp.dto.PaginationDTO;
import com.webapp.entity.Alert;
import com.webapp.util.WebAppUtil;

@EnableAutoConfiguration
@RestController
@ComponentScan
@RequestMapping("/webapp/alert")
@ImportResource("classpath:Beans.xml")
public class AlertController {

	@Autowired
	AlertBO alertBO;
	
	@ResponseBody
	@RequestMapping(value = "/getAllAlerts", method = RequestMethod.POST)
	private ResponseEntity getAllAlerts(@RequestBody PaginationDTO paginationDTO) {
		ResponseEntity responseEntity = alertBO.getAllAlerts(paginationDTO);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAlertInformation", method = RequestMethod.GET)
	private ResponseEntity getAlertInformation(@RequestParam(value = "alertId", required = true) String alertId) {
		ResponseEntity responseEntity = alertBO.getAlertInformation(alertId);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateAlertUpdateMessage", method = RequestMethod.POST)
	private ResponseEntity updateAlertUpdateMessage(@RequestBody Alert reqAlert) {
		ResponseEntity responseEntity = alertBO.updateAlertUpdateMessage(reqAlert);
		return responseEntity;
	}
	
	
}
