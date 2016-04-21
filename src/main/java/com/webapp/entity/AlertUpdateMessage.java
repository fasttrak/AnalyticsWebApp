package com.webapp.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="Alerts")
public class AlertUpdateMessage {

	@Field
	private String updateMessage;
	
	@Field
	private Date updateDateTime;
	
	@Field
	private User updatedByUser;

	public String getUpdateMessage() {
		return updateMessage;
	}

	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public User getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(User updatedByUser) {
		this.updatedByUser = updatedByUser;
	}
	
}
