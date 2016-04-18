package com.webapp.dto;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class GroupDTO {

	private String id;
	private String name;
	private String description;
	private List<MapDTO> tabAccess;
	
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
	public List<MapDTO> getTabAccess() {
		return tabAccess;
	}
	public void setTabAccess(List<MapDTO> tabAccess) {
		this.tabAccess = tabAccess;
	}
	
}
