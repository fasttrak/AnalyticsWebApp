package com.webapp.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.webapp.dto.MapDTO;
import com.webapp.dto.PaginationDTO;
import com.webapp.entity.Alert;
import com.webapp.entity.AlertUpdateMessage;
import com.webapp.entity.User;
import com.webapp.repository.AlertRepository;

public class AlertDAO {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	AlertRepository alertRepository;
	
	public PaginationDTO getAllAlerts(PaginationDTO paginationDTO, Set<String> validColumnNames)throws Exception{
		PaginationDTO responsePaginationDTO=null;
		try{
		    responsePaginationDTO=new PaginationDTO();
		    List<Alert> alerts=getPaginationAlerts(paginationDTO, validColumnNames);
		    for(Alert alert:alerts){
		    	Calendar calendar=Calendar.getInstance();
		    	calendar.set(alert.getYear(), alert.getMonth()-1, alert.getDay(), alert.getHour(), alert.getMinute(), alert.getSecond());
		    	alert.setCreateDateTime(calendar.getTime());
		    }
		    responsePaginationDTO.setData(alerts);
		    long totalRecords=getPaginationAlertsCount(paginationDTO, validColumnNames);
		    responsePaginationDTO.setTotalRecords(totalRecords);
		}catch(Exception e){
			throw e;
		}
		return responsePaginationDTO;
	}
	
	private List<Alert> getPaginationAlerts(PaginationDTO paginationDTO, Set<String> validColumnNames)throws Exception{
		List<Alert> alerts=null;
		try{
			Query query = new Query();
			List<MapDTO> filterMaps=paginationDTO.getFilterParams();
			if(filterMaps!=null){
				for(MapDTO mapDTO:filterMaps){
					if(validColumnNames.contains(mapDTO.getKey())){
						query.addCriteria(Criteria.where(mapDTO.getKey()).regex(mapDTO.getValueString()));
					}
				}
			}
			List<MapDTO> sortingMaps=paginationDTO.getSortingParams();
			if(sortingMaps!=null){
				for(MapDTO mapDTO:sortingMaps){
					if(validColumnNames.contains(mapDTO.getKey())){
						Direction sortDirection=Direction.DESC;
						if("asc".equals(mapDTO.getValueString())){
							sortDirection=Direction.ASC;
						}
						query.with(new Sort(new Order(sortDirection, mapDTO.getKey())));
					}
				}
			}
			query.with(new Sort(new Order(Direction.DESC, "id")));
			query.skip((paginationDTO.getPage()-1)*paginationDTO.getSize());
		    query.limit(paginationDTO.getSize());
		    alerts = mongoTemplate.find(query, Alert.class);
		    for(Alert alert:alerts){
		    	Calendar calendar=Calendar.getInstance();
		    	calendar.set(alert.getYear(), alert.getMonth()-1, alert.getDay(), alert.getHour(), alert.getMinute(), alert.getSecond());
		    	alert.setCreateDateTime(calendar.getTime());
		    }
		}catch(Exception e){
			throw e;
		}
		return alerts;
	}
	
	private long getPaginationAlertsCount(PaginationDTO paginationDTO, Set<String> validColumnNames)throws Exception{
		long alertsCount=0l;
		try{
			Query query = new Query();
			List<MapDTO> filterMaps=paginationDTO.getFilterParams();
			if(filterMaps!=null){
				for(MapDTO mapDTO:filterMaps){
					if(validColumnNames.contains(mapDTO.getKey())){
						query.addCriteria(Criteria.where(mapDTO.getKey()).regex(mapDTO.getValueString()));
					}
				}
			}
			alertsCount = mongoTemplate.count(query, Alert.class);
		}catch(Exception e){
			throw e;
		}
		return alertsCount;
	}
	
	public Alert getAlertInformation(String alertId){
		Alert alert=null;
		try{
			alert=alertRepository.findOne(alertId);
			Calendar calendar=Calendar.getInstance();
	    	calendar.set(alert.getYear(), alert.getMonth()-1, alert.getDay(), alert.getHour(), alert.getMinute(), alert.getSecond());
	    	alert.setCreateDateTime(calendar.getTime());
		}catch(Exception e){
			throw e;
		}
		return alert;		
	}
	
	public Alert updateAlertUpdateMessage(Alert reqAlert){
		Alert alert=null;
		try{
			String alertId=reqAlert.getId();
			alert=alertRepository.findOne(alertId);
			alert.setAssignedTo(reqAlert.getAssignedTo());
			if(reqAlert.getAlertUpdateMessages().size()>0){
				AlertUpdateMessage alertUpdateMessage=reqAlert.getAlertUpdateMessages().get(0);
				if(alertUpdateMessage.getUpdateMessage()!=null && !alertUpdateMessage.getUpdateMessage().isEmpty()){
					alertUpdateMessage.setUpdateDateTime(new Date());
					if(alert.getAlertUpdateMessages()==null){
						alert.setAlertUpdateMessages(new ArrayList<AlertUpdateMessage>());
					}
					alert.getAlertUpdateMessages().add(alertUpdateMessage);
				}
				alert.setDescription(reqAlert.getDescription());
				alertRepository.save(alert);
			}
		}catch(Exception e){
			throw e;
		}
		return alert;		
	}
	
}
