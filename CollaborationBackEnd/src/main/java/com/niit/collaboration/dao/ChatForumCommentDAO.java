package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.ChatForumComment;

@Repository		
public interface ChatForumCommentDAO {
	public boolean save(ChatForumComment chatforumcomment);
	
	public boolean update(ChatForumComment chatforumcomment);
	
	public boolean saveOrUpdate(ChatForumComment chatforumcomment);
	
	public boolean delete(ChatForumComment chatforumcomment);
	
	public ChatForumComment get(String id);
	
	public List<ChatForumComment> list();
}


