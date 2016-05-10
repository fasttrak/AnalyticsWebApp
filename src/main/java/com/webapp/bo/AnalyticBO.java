package com.webapp.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.webapp.dao.AnalyticDAO;
import com.webapp.entity.Analytic;
import com.webapp.entity.MLData;

public class AnalyticBO {

	@Autowired
	AnalyticDAO analyticDAO;
	
	public ResponseEntity getAllAnalytics() {
		List<MLData> analytics=null;
		try{
			analytics=analyticDAO.getAllAnalytics();
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<MLData>>(analytics, HttpStatus.OK);
	}
	
}
