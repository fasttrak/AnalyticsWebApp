package com.webapp.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="Users")
public class User {

	@Id
    private String id;
	
	@Field
	private String name;
	
	@Field
	private String position;
	
	@Field
	private String imagePath;
	
	@Field
	private String username;
	
	@Field
	private String password;
	
	@Field
	private Integer sessionId;
	
	@Field
	private String role;

	
	//request, response fields
	private String oldPassword;
	
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(!(obj instanceof User)){
			return false;
		}
		if(this==obj){
			return true;
		}
		User user=(User)obj;
		if(!this.username.equals(user.username)){
			return false;
		}
		if(!this.password.equals(user.password)){
			return false;
		}
		if(!this.name.equals(user.name)){
			return false;
		}
		if(!this.imagePath.equals(user.imagePath)){
			return false;
		}
		if(!this.id.equals(user.id)){
			return false;
		}
		if(!this.position.equals(user.position)){
			return false;
		}
		if(!this.role.equals(user.role)){
			return false;
		}
		return true;
	}
}
