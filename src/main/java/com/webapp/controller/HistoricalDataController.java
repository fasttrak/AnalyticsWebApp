package com.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.bo.HistoricalDataBO;
import com.webapp.dto.HistoricalDataDTO;
import com.webapp.dto.PaginationDTO;

@EnableAutoConfiguration
@RestController
@ComponentScan
@RequestMapping("/webapp/historicaldata")
@ImportResource("classpath:Beans.xml")
public class HistoricalDataController {

	@Autowired
	HistoricalDataBO historicalDataBO;
	
	@ResponseBody
	@RequestMapping(value = "/getHistoricalData", method = RequestMethod.POST)
	private ResponseEntity getHistoricalData(@RequestBody HistoricalDataDTO historicalDataDTO) {
		ResponseEntity responseEntity = historicalDataBO.getHistoricalData(historicalDataDTO);
		return responseEntity;
	}
	
}
