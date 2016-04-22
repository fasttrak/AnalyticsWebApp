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
	
	//cpu utilization
	@Field(value="one_hour_cpu_analytics_intercept")
	private Double oneHourCPUAnalyticsIntercept;
	
	@Field(value="one_hour_cpu_analytics_slope")
	private Double oneHourCPUAnalyticsSlope;
	
	@Field(value="one_day_cpu_analytics_intercept")
	private Double oneDayCPUAnalyticsIntercept;
	
	@Field(value="one_day_cpu_analytics_slope")
	private Double oneDayCPUAnalyticsSlope;
	
	@Field(value="one_month_cpu_analytics_intercept")
	private Double oneMonthCPUAnalyticsIntercept;
	
	@Field(value="one_month_cpu_analytics_slope")
	private Double oneMonthCPUAnalyticsSlope;
	
	//memory utilization
	@Field(value="one_hour_memory_analytics_intercept")
	private Double oneHourMemoryAnalyticsIntercept;
	
	@Field(value="one_hour_memory_analytics_slope")
	private Double oneHourMemoryAnalyticsSlope;
	
	@Field(value="one_day_memory_analytics_intercept")
	private Double oneDayMemoryAnalyticsIntercept;
	
	@Field(value="one_day_memory_analytics_slope")
	private Double oneDayMemoryAnalyticsSlope;
	
	@Field(value="one_month_memory_analytics_intercept")
	private Double oneMonthMemoryAnalyticsIntercept;
	
	@Field(value="one_month_memory_analytics_slope")
	private Double oneMonthMemoryAnalyticsSlope;

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

	public Double getOneDayCPUAnalyticsIntercept() {
		return oneDayCPUAnalyticsIntercept;
	}

	public void setOneDayCPUAnalyticsIntercept(Double oneDayCPUAnalyticsIntercept) {
		this.oneDayCPUAnalyticsIntercept = oneDayCPUAnalyticsIntercept;
	}

	public Double getOneDayCPUAnalyticsSlope() {
		return oneDayCPUAnalyticsSlope;
	}

	public void setOneDayCPUAnalyticsSlope(Double oneDayCPUAnalyticsSlope) {
		this.oneDayCPUAnalyticsSlope = oneDayCPUAnalyticsSlope;
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

	public Double getOneHourMemoryAnalyticsIntercept() {
		return oneHourMemoryAnalyticsIntercept;
	}

	public void setOneHourMemoryAnalyticsIntercept(Double oneHourMemoryAnalyticsIntercept) {
		this.oneHourMemoryAnalyticsIntercept = oneHourMemoryAnalyticsIntercept;
	}

	public Double getOneHourMemoryAnalyticsSlope() {
		return oneHourMemoryAnalyticsSlope;
	}

	public void setOneHourMemoryAnalyticsSlope(Double oneHourMemoryAnalyticsSlope) {
		this.oneHourMemoryAnalyticsSlope = oneHourMemoryAnalyticsSlope;
	}

	public Double getOneDayMemoryAnalyticsIntercept() {
		return oneDayMemoryAnalyticsIntercept;
	}

	public void setOneDayMemoryAnalyticsIntercept(Double oneDayMemoryAnalyticsIntercept) {
		this.oneDayMemoryAnalyticsIntercept = oneDayMemoryAnalyticsIntercept;
	}

	public Double getOneDayMemoryAnalyticsSlope() {
		return oneDayMemoryAnalyticsSlope;
	}

	public void setOneDayMemoryAnalyticsSlope(Double oneDayMemoryAnalyticsSlope) {
		this.oneDayMemoryAnalyticsSlope = oneDayMemoryAnalyticsSlope;
	}

	public Double getOneMonthMemoryAnalyticsIntercept() {
		return oneMonthMemoryAnalyticsIntercept;
	}

	public void setOneMonthMemoryAnalyticsIntercept(Double oneMonthMemoryAnalyticsIntercept) {
		this.oneMonthMemoryAnalyticsIntercept = oneMonthMemoryAnalyticsIntercept;
	}

	public Double getOneMonthMemoryAnalyticsSlope() {
		return oneMonthMemoryAnalyticsSlope;
	}

	public void setOneMonthMemoryAnalyticsSlope(Double oneMonthMemoryAnalyticsSlope) {
		this.oneMonthMemoryAnalyticsSlope = oneMonthMemoryAnalyticsSlope;
	}

}
