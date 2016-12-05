package com.niit.collaboration.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "n_blog")
@Component

public class Blog extends BaseDomain {
	
@Id
private String id;
private String title;
private String description;
private String UserId;
private String reason;
private String status;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getUserId() {
	return UserId;
}
public void setUserId(String userId) {
	UserId = userId;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}

public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getDatetime() {
	return datetime;
}
public void setDatetime(Date datetime) {
	this.datetime = datetime;
}
@Transient
private Date datetime;







}
