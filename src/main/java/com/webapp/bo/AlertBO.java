package com.webapp.bo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.webapp.dao.AlertDAO;
import com.webapp.dao.UserDAO;
import com.webapp.dto.PaginationDTO;
import com.webapp.entity.Alert;

public class AlertBO {

	@Autowired
	AlertDAO alertDAO;
	
	public ResponseEntity getAllAlerts(PaginationDTO paginationDTO) {
		PaginationDTO responsePaginationDTO=null;
		try{
			Set<String> validColumnNames=new HashSet<String>();
			validColumnNames.add("message");
			validColumnNames.add("createDateTime");
			responsePaginationDTO=alertDAO.getAllAlerts(paginationDTO, validColumnNames);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PaginationDTO>(responsePaginationDTO, HttpStatus.OK);
	}

	public ResponseEntity getAlertInformation(String alertId) {
		Alert alert=null;
		try{
			alert=alertDAO.getAlertInformation(alertId);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Alert>(alert, HttpStatus.OK);
	}
	
	public ResponseEntity updateAlertUpdateMessage(Alert reqAlert) {
		Alert alert=null;
		try{
			alert=alertDAO.updateAlertUpdateMessage(reqAlert);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Alert>(alert, HttpStatus.OK);
	}
	
}
