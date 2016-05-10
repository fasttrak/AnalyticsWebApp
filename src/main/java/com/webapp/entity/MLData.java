package com.webapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="mldata")
public class MLData {

	@Id
    private String id;
	
	@Field(value="intercept")
    private double intercept;
	
	@Field(value="hour")
    private double hour;
	
	@Field(value="min")
    private double min;
	
	@Field(value="hout_mean")
    private double hourMean;
	
	@Field(value="minute_mean")
    private double minuteMean;
	
	@Field(value="hour_std")
    private double hourStd;
	
	@Field(value="minute_std")
    private double minuteStd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getIntercept() {
		return intercept;
	}

	public void setIntercept(double intercept) {
		this.intercept = intercept;
	}

	public double getHour() {
		return hour;
	}

	public void setHour(double hour) {
		this.hour = hour;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getHourMean() {
		return hourMean;
	}

	public void setHourMean(double hourMean) {
		this.hourMean = hourMean;
	}

	public double getMinuteMean() {
		return minuteMean;
	}

	public void setMinuteMean(double minuteMean) {
		this.minuteMean = minuteMean;
	}

	public double getHourStd() {
		return hourStd;
	}

	public void setHourStd(double hourStd) {
		this.hourStd = hourStd;
	}

	public double getMinuteStd() {
		return minuteStd;
	}

	public void setMinuteStd(double minuteStd) {
		this.minuteStd = minuteStd;
	}

}
