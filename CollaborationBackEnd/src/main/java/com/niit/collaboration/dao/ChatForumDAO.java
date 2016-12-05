package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.ChatForum;
import com.niit.collaboration.model.ChatForumComment;

@Repository		
public interface ChatForumDAO {
	public boolean save(ChatForum chatforum);	
	public boolean saveComment(ChatForumComment chatchatforumComment);		//chatforumComment
	
	public boolean update(ChatForum chatforum);
		
	public boolean delete(ChatForum chatforum);
		
	public ChatForum get(int id);
	public ChatForumComment getComment(int id);			//chatforumComment
	
	public List<ChatForum> list();	
	public List<ChatForumComment> listComment(String id);		//chatforumComment
}



