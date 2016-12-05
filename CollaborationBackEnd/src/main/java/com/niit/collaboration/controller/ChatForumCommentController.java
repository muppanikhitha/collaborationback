package com.niit.collaboration.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.ChatForumCommentDAO;
import com.niit.collaboration.model.ChatForumComment;


@RestController
public class ChatForumCommentController {
Logger log = Logger.getLogger(ChatForumCommentController.class);
	
	@Autowired
	ChatForumCommentDAO chatforumCommentDAO;
	@GetMapping(value = "/chatforumComments")
	public ResponseEntity<List<ChatForumComment>> listChatForumComments() {
		List<ChatForumComment> chatforumComment = chatforumCommentDAO.list();
		if(chatforumComment.isEmpty()) {
			return new ResponseEntity<List<ChatForumComment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ChatForumComment>>(chatforumComment, HttpStatus.OK);
	}
	@PostMapping(value = "/chatforumComment/")
	public ResponseEntity<ChatForumComment> createChatForumComment(@RequestBody ChatForumComment chatforumComment) {
		if(chatforumCommentDAO.get(chatforumComment.getId()) == null) {
			chatforumCommentDAO.save(chatforumComment);
			return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.OK);
		}
		chatforumComment.setErrMessage("ChatForumComment already exist with id : " +chatforumComment.getId());
		return new ResponseEntity<ChatForumComment>(HttpStatus.OK);
	}
	@PutMapping(value = "/chatforumComment/{id}")
	public ResponseEntity<ChatForumComment> updateChatForumComment(@PathVariable("id") String id, @RequestBody ChatForumComment chatforumComment) {
		if(chatforumCommentDAO.get(id) == null) {
			chatforumComment = new ChatForumComment();
			chatforumComment.setErrMessage("No forumComment exist with id : " +chatforumComment.getId());
			return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.NOT_FOUND);
		}
		chatforumCommentDAO.update(chatforumComment);
		return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.OK);
	}
	@DeleteMapping(value = "/chatforumComment/{id}")
	public ResponseEntity<ChatForumComment> deleteChatForumComment(@PathVariable("id") String id) {
		ChatForumComment chatforumComment = chatforumCommentDAO.get(id);
		if(chatforumComment == null) {
			chatforumComment = new ChatForumComment();
			chatforumComment.setErrMessage("No forumComment exist with id : " + id);
			return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.NOT_FOUND);
		}
		chatforumCommentDAO.delete(chatforumComment);
		return new ResponseEntity<ChatForumComment>(HttpStatus.OK);		
	}
	@GetMapping(value = "/chatforumComment/{id}")
	public ResponseEntity<ChatForumComment> getChatForumComment(@PathVariable("id") String id) {
		ChatForumComment chatforumComment = chatforumCommentDAO.get(id);
		if(chatforumComment == null) {
			chatforumComment = new ChatForumComment();
			chatforumComment.setErrMessage("No forumComment exist with id : " + id);
			return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.OK);
	}
}
