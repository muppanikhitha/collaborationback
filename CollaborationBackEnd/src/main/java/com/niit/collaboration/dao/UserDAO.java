package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.User;

@Repository
public interface UserDAO {
public boolean save(User user);
	
	public boolean update(User user);
	
	public boolean saveOrUpdate(User user);
	
	public boolean delete(User user);
	
	public User get(String id);
	
	public List<User> list();
	
	public User authenticate(String id, String password);
}
