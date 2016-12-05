package com.niit.collaboration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "n_FORUMCOMMENT")
@Component
public class ChatForumComment extends BaseDomain implements Serializable {

	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_FORUM_COMMENT_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	private int id;
	
	private String forumId;
	private String userId;
	
	@Column(name = "commented_date")
	private Date commentedDate;
private String message;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getForumId() {
	return forumId;
}
public void setForumId(String forumId) {
	this.forumId = forumId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public Date getCommentedDate() {
	return commentedDate;
}
public void setCommentedDate(Date commentedDate) {
	this.commentedDate = commentedDate;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}
