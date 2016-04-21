package com.webapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="Analytics")
public class Analytic {

	@Id
    private String id;
	
	@Field(value="ip")
    private String ip;
	
	@Field(value="one_hour_cpu_analytics_intercept")
	private Double oneHourCPUAnalyticsIntercept;
	
	@Field(value="one_hour_cpu_analytics_slope")
	private Double oneHourCPUAnalyticsSlope;
	
	@Field(value="one_day_cpu_analytics_intercept")
	private Double oneWeekCPUAnalyticsIntercept;
	
	@Field(value="one_day_cpu_analytics_slope")
	private Double oneWeekCPUAnalyticsSlope;
	
	@Field(value="one_month_cpu_analytics_intercept")
	private Double oneMonthCPUAnalyticsIntercept;
	
	@Field(value="one_month_cpu_analytics_slope")
	private Double oneMonthCPUAnalyticsSlope;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Double getOneHourCPUAnalyticsIntercept() {
		return oneHourCPUAnalyticsIntercept;
	}

	public void setOneHourCPUAnalyticsIntercept(Double oneHourCPUAnalyticsIntercept) {
		this.oneHourCPUAnalyticsIntercept = oneHourCPUAnalyticsIntercept;
	}

	public Double getOneHourCPUAnalyticsSlope() {
		return oneHourCPUAnalyticsSlope;
	}

	public void setOneHourCPUAnalyticsSlope(Double oneHourCPUAnalyticsSlope) {
		this.oneHourCPUAnalyticsSlope = oneHourCPUAnalyticsSlope;
	}

	public Double getOneWeekCPUAnalyticsIntercept() {
		return oneWeekCPUAnalyticsIntercept;
	}

	public void setOneWeekCPUAnalyticsIntercept(Double oneWeekCPUAnalyticsIntercept) {
		this.oneWeekCPUAnalyticsIntercept = oneWeekCPUAnalyticsIntercept;
	}

	public Double getOneWeekCPUAnalyticsSlope() {
		return oneWeekCPUAnalyticsSlope;
	}

	public void setOneWeekCPUAnalyticsSlope(Double oneWeekCPUAnalyticsSlope) {
		this.oneWeekCPUAnalyticsSlope = oneWeekCPUAnalyticsSlope;
	}

	public Double getOneMonthCPUAnalyticsIntercept() {
		return oneMonthCPUAnalyticsIntercept;
	}

	public void setOneMonthCPUAnalyticsIntercept(Double oneMonthCPUAnalyticsIntercept) {
		this.oneMonthCPUAnalyticsIntercept = oneMonthCPUAnalyticsIntercept;
	}

	public Double getOneMonthCPUAnalyticsSlope() {
		return oneMonthCPUAnalyticsSlope;
	}

	public void setOneMonthCPUAnalyticsSlope(Double oneMonthCPUAnalyticsSlope) {
		this.oneMonthCPUAnalyticsSlope = oneMonthCPUAnalyticsSlope;
	}

}
