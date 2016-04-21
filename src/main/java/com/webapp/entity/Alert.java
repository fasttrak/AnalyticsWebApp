package com.webapp.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="Alerts")
public class Alert {

	@Id
    private String id;
	
	@Field
	private String type;
	
	@Field
	private String message;
	
	@Field
	private String description;
	
	@Field
	private Date createDateTime;
	
	@Field
	private String assignedTo;
	
	@Field
	private List<AlertUpdateMessage> alertUpdateMessages;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public List<AlertUpdateMessage> getAlertUpdateMessages() {
		return alertUpdateMessages;
	}

	public void setAlertUpdateMessages(List<AlertUpdateMessage> alertUpdateMessages) {
		this.alertUpdateMessages = alertUpdateMessages;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

}
