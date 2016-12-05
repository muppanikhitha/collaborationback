package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.niit.collaboration.dao.ChatForumDAO;
import com.niit.collaboration.model.ChatForum;
import com.niit.collaboration.model.ChatForumComment;
import com.niit.collaboration.model.User;


@RestController
public class ChatForumController {
Logger log = Logger.getLogger(ChatForumController.class);
	
	@Autowired
	ChatForumDAO chatforumDAO;
	
	@Autowired
	ChatForum chatforum;
	
	/**
	 * @return
	 */
	@GetMapping(value = "/chatforums")
	public ResponseEntity<List<ChatForum>> listChatForums() {
		log.debug("**********Starting of listChatForums() method.");
		List<ChatForum> forum = chatforumDAO.list();
		if(forum.isEmpty()) {
			return new ResponseEntity<List<ChatForum>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listChatForums() method.");
		return new ResponseEntity<List<ChatForum>>(forum, HttpStatus.OK);
	}
	
	/**
	 * @param forum
	 * @return
	 */
	@PostMapping(value = "/forum/")
	public ResponseEntity<ChatForum> createChatForum(@RequestBody ChatForum forum, HttpSession session) {
		log.debug("**********Starting of createChatForum() method.");
		if(chatforumDAO.get(forum.getId()) == null) {
			
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			
			forum.setUserId(loggedInUser.getId());
			chatforumDAO.save(forum);
			
			log.debug("**********End of createChatForum() method.");
			return new ResponseEntity<ChatForum>(forum, HttpStatus.OK);
		}
		forum.setErrMessage("ChatForum already exist with id : " +forum.getId());
		log.error("ChatForum already exist with id : " +forum.getId());
		return new ResponseEntity<ChatForum>(forum, HttpStatus.OK);
	}
	
	/**
	 * @param id
	 * @param forum
	 * @return
	 */
	@PutMapping(value = "/forum/{id}")
	public ResponseEntity<ChatForum> updateChatForum(@PathVariable("id") int id, @RequestBody ChatForum forum) {
		log.debug("**********Starting of updateChatForum() method.");
		if(chatforumDAO.get(id) == null) {
			forum = new ChatForum();
			forum.setErrMessage("No forum exist with id : " +forum.getId());
			log.error("No forum exist with id : " +forum.getId());
			return new ResponseEntity<ChatForum>(forum, HttpStatus.NOT_FOUND);
		}
		chatforumDAO.update(forum);
		log.debug("**********End of updateChatForum() method.");
		return new ResponseEntity<ChatForum>(forum, HttpStatus.OK);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/forum/{id}")
	public ResponseEntity<ChatForum> deleteChatForum(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteChatForum() method.");
		ChatForum forum = chatforumDAO.get(id);
		if(forum == null) {
			forum = new ChatForum();
			forum.setErrMessage("No forum exist with id : " + id);
			log.error("No forum exist with id : " + id);
			return new ResponseEntity<ChatForum>(forum, HttpStatus.NOT_FOUND);
		}
		chatforumDAO.delete(forum);
		log.debug("**********End of deleteChatForum() method.");
		return new ResponseEntity<ChatForum>(HttpStatus.OK);		
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/forum/{id}")
	public ResponseEntity<ChatForum> getChatForum(@PathVariable("id") int id) {
		log.debug("**********Starting of getChatForum() method.");
		ChatForum forum = chatforumDAO.get(id);
		if(forum == null) {
			forum = new ChatForum();
			forum.setErrMessage("No forum exist with id : " + id);
			log.error("No forum exist with id : " + id);
			return new ResponseEntity<ChatForum>(forum, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getChatForum() method.");
		return new ResponseEntity<ChatForum>(forum, HttpStatus.OK);
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|ChatForum Comment Area|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	/**
	 * http://localhost:8081/Binder/chatforumComments						[working]
	 * @return
	 */
	@GetMapping(value = "/chatforumComments/{forumId}")
	public ResponseEntity<List<ChatForumComment>> listChatForumComments(@PathVariable("forumId") String forumId) {
		log.debug("**********Starting of listChatForumComments() method.");
		List<ChatForumComment> chatforumComment = chatforumDAO.listComment(forumId);
		if(chatforumComment.isEmpty()) {
			return new ResponseEntity<List<ChatForumComment>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listChatForumComments() method.");
		return new ResponseEntity<List<ChatForumComment>>(chatforumComment, HttpStatus.OK);
	}
	
	/**
	 * @param chatforumComment
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/chatforumComment/")
	public ResponseEntity<ChatForumComment> createChatForumComment(@RequestBody ChatForumComment chatforumComment, HttpSession session) {
		log.debug("**********Starting of createChatForumComment() method.");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		chatforumComment.setUserId(loggedInUser.getId());
		
		chatforumDAO.saveComment(chatforumComment);
		log.debug("**********End of createChatForumComment() method.");
		return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8081/Binder/chatforumComment/{id}							[working]
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/chatforumComment/{id}")
	public ResponseEntity<ChatForumComment> getChatForumComment(@PathVariable("id") int id) {
		log.debug("**********Starting of getChatForumComment() method.");
		ChatForumComment chatforumComment = chatforumDAO.getComment(id);
		if(chatforumComment == null) {
			chatforumComment = new ChatForumComment();
			chatforumComment.setErrMessage("No getChatForumComment exist with id : " + id);
			log.error("No getChatForumComment exist with id : " + id);
			return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getChatForumComment() method.");
		return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.OK);
	}
}


