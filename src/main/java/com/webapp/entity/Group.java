package com.webapp.entity;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="Groups")
public class Group {
	
	@Id
	private String id;
	
	@Field
	private String name;
	
	@Field
	private String description;
	
	@Field
	private Map<String, Boolean> tabAccess;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Map<String, Boolean> getTabAccess() {
		return tabAccess;
	}
	public void setTabAccess(Map<String, Boolean> tabAccess) {
		this.tabAccess = tabAccess;
	}
	
}
