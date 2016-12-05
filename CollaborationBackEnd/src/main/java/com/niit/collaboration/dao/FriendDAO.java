package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Friend;

@Repository
public interface FriendDAO {
	public boolean save(Friend friend);				
	
	public boolean update(Friend friend);							
			
	public Friend getSelectedFriend(String userId, String friendId);		
	public Friend get(int id);
	public List<Friend> getMyFriends(String userId);			
	
	public List<Friend> getNewFriendRequests(String userId);		
	public void rejectFriend(String userId);			
	
	public void setOnline(String userId);					
	public void setOffline(String userId);				
	public boolean isFriend(String userId, String friendId);	

}
