package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Job;

@Repository	

public interface JobDAO {
public boolean save(Job job);
	
	public boolean update(Job job);
	
	public boolean saveOrUpdate(Job job);
	
	public boolean delete(Job job);
	
	public Job get(String id);
	
	public List<Job> list();
}
