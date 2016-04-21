package com.webapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.webapp.entity.Analytic;
import com.webapp.repository.AnalyticRepository;

public class AnalyticDAO {

	@Autowired
	AnalyticRepository analyticRepository;
	
	public List<Analytic> getAllAnalytics()throws Exception{
		List<Analytic> analytics=null;
		try{
			analytics=analyticRepository.findAll();
		}catch(Exception e){
			throw e;
		}
		return analytics;
	}
	
}
