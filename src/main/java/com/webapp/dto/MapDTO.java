package com.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapDTO {

	private String key;
	private boolean value;
	private String valueString;
	private Integer intString;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	public String getValueString() {
		return valueString;
	}
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
	public Integer getIntString() {
		return intString;
	}
	public void setIntString(Integer intString) {
		this.intString = intString;
	}
	
}
