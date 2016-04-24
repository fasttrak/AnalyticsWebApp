package com.webapp.bo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.webapp.dao.AlertDAO;
import com.webapp.dao.UserDAO;
import com.webapp.dto.MapDTO;
import com.webapp.dto.PaginationDTO;
import com.webapp.entity.Alert;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.Properties;

public class AlertBO {

	@Autowired
	AlertDAO alertDAO;
	
	public ResponseEntity getAllAlerts(PaginationDTO paginationDTO) {
		PaginationDTO responsePaginationDTO=null;
		try{
			Set<String> validColumnNames=new HashSet<String>();
			validColumnNames.add("type");
			validColumnNames.add("ip");
			validColumnNames.add("value");
			validColumnNames.add("description");
			validColumnNames.add("assignedTo");
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

	public ResponseEntity updateAlertConfig(List<MapDTO> mapDTOs) {
		try{
			int cpuThreshold=-1;
			int memoryThreshold=-1;
			for(MapDTO mapDTO:mapDTOs){
				if("cpu".equals(mapDTO.getKey())){
					cpuThreshold=mapDTO.getIntString();
				}else if("memory".equals(mapDTO.getKey())){
					memoryThreshold=mapDTO.getIntString();
				}
			}
			updateThresholds(cpuThreshold, memoryThreshold);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	private void updateThresholds(Integer cpu, Integer memeory) {
		
    }
	
}
