package com.webapp.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webapp.entity.Daily;
import com.webapp.entity.Hourly;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalDataDTO {

	private Date startDate;
	private Date endDate;
	
	private List<Daily> dailyDocuments;
	private List<Hourly> hourlyDocuments;
	
	private List<String> xAxisData;
	private SeriesData yAxisSeriesData;
	
	private String ip;
	private String flow;
	private String type;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<Daily> getDailyDocuments() {
		return dailyDocuments;
	}
	public void setDailyDocuments(List<Daily> dailyDocuments) {
		this.dailyDocuments = dailyDocuments;
	}
	public List<Hourly> getHourlyDocuments() {
		return hourlyDocuments;
	}
	public void setHourlyDocuments(List<Hourly> hourlyDocuments) {
		this.hourlyDocuments = hourlyDocuments;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public List<String> getxAxisData() {
		return xAxisData;
	}
	public void setxAxisData(List<String> xAxisData) {
		this.xAxisData = xAxisData;
	}
	public SeriesData getyAxisSeriesData() {
		return yAxisSeriesData;
	}
	public void setyAxisSeriesData(SeriesData yAxisSeriesData) {
		this.yAxisSeriesData = yAxisSeriesData;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}


