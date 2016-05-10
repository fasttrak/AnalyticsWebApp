package com.webapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.webapp.entity.Analytic;
import com.webapp.entity.MLData;
import com.webapp.repository.AnalyticRepository;
import com.webapp.repository.MLDataRepository;

public class AnalyticDAO {

	@Autowired
	AnalyticRepository analyticRepository;
	
	@Autowired
	MLDataRepository mlDataRepository;
	
	public List<MLData> getAllAnalytics()throws Exception{
		List<MLData> analytics=null;
		try{
			//analytics=analyticRepository.findAll();
			analytics=mlDataRepository.findAll();
		}catch(Exception e){
			throw e;
		}
		return analytics;
	}
	
}
