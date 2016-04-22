package com.webapp.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.webapp.dao.HistoricalDataDAO;
import com.webapp.dto.HistoricalDataDTO;
import com.webapp.dto.PaginationDTO;
import com.webapp.dto.SeriesData;
import com.webapp.entity.Daily;
import com.webapp.entity.Hourly;

public class HistoricalDataBO {

	@Autowired
	HistoricalDataDAO historicalDataDAO;

	public ResponseEntity getHistoricalData(HistoricalDataDTO historicalDataDTO) {
		try{
			String ip=historicalDataDTO.getIp();
			Date startDate=historicalDataDTO.getStartDate();
			Date endDate=historicalDataDTO.getEndDate();
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startDate);
			int startDay=calendar.get(Calendar.DATE);
			int startMonth=calendar.get(Calendar.MONTH)+1;
			int startYear=calendar.get(Calendar.YEAR);
			calendar.setTime(endDate);
			int endDay=calendar.get(Calendar.DATE);
			int endMonth=calendar.get(Calendar.MONTH)+1;
			int endYear=calendar.get(Calendar.YEAR);
			if(startDay==endDay && startMonth==endMonth && startYear==endYear){
				//hourly flow
				List<Hourly> hourlyList=historicalDataDAO.findHourlyDocumentsForDay(ip, startDate);
				historicalDataDTO.setHourlyDocuments(hourlyList);
				historicalDataDTO.setFlow("hourly");
			}else{
				//daily flow
				List<Daily> dailyList=historicalDataDAO.findDailyDocumentsBetweenDays(ip, startDate, endDate);
				historicalDataDTO.setDailyDocuments(dailyList);
				historicalDataDTO.setFlow("daily");
			}
			//create graph required 
			calculateGraphData(historicalDataDTO);
			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<HistoricalDataDTO>(historicalDataDTO, HttpStatus.OK);
	}

	private SeriesData calculateGraphData(HistoricalDataDTO historicalDataDTO){
		String flowName=historicalDataDTO.getFlow();
		String host=historicalDataDTO.getIp();
		SeriesData seriesData=new SeriesData();
		historicalDataDTO.setyAxisSeriesData(seriesData);
		List<String> xAxisData=new ArrayList<String>();
		historicalDataDTO.setxAxisData(xAxisData);
		seriesData.setName(host);
		seriesData.setData(new ArrayList<Double>());
		if("hourly".equals(historicalDataDTO.getFlow())){
			for(Hourly hourly:historicalDataDTO.getHourlyDocuments()){
				if("cpu".equals(historicalDataDTO.getType())){
					seriesData.getData().add(hourly.getCpu()/hourly.getWriteCount());
				}else{
					seriesData.getData().add(hourly.getMemory()/hourly.getWriteCount());
				}
				xAxisData.add(Integer.toString(hourly.getHour()));
			}
		}else{
			for(Daily daily:historicalDataDTO.getDailyDocuments()){
				if("cpu".equals(historicalDataDTO.getType())){
					seriesData.getData().add(daily.getCpu()/daily.getWriteCount());
				}else{
					seriesData.getData().add(daily.getMemory()/daily.getWriteCount());
				}
				xAxisData.add(Integer.toString(daily.getDay()));
			}
		}
		return seriesData;
	}
}
