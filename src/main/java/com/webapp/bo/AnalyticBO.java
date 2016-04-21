package com.webapp.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.webapp.dao.AnalyticDAO;
import com.webapp.entity.Analytic;

public class AnalyticBO {

	@Autowired
	AnalyticDAO analyticDAO;
	
	public ResponseEntity getAllAnalytics() {
		List<Analytic> analytics=null;
		try{
			analytics=analyticDAO.getAllAnalytics();
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Analytic>>(analytics, HttpStatus.OK);
	}
	
}
