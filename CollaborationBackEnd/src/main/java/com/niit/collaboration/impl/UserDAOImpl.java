package com.niit.collaboration.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;
@EnableTransactionManagement
@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO {
	Logger log = Logger.getLogger(UserDAOImpl.class);	
	@Autowired	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public UserDAOImpl() { 
		
	}	
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public boolean save(User user){
		try {
			log.debug("**********Starting of save() method.");
			sessionFactory.getCurrentSession().save(user);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(User user){
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(user);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(User user) {
		try {
			log.debug("**********Starting of saveOrUpdate() method.");
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			log.debug("**********End of saveOrUpdate() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(User user) {
		try {
			log.debug("**********Starting of delete() method.");
			sessionFactory.getCurrentSession().delete(user);
			log.debug("**********End of delete() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public User get(String id) {
		log.debug("**********Starting of get() method.");
		String hql = "from User where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> list() {
		log.debug("**********Starting of list() method.");
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of list() method.");
		return query.list();
	}	
	
	@Transactional
	public User authenticate(String id, String password) {
		log.debug("**********Starting of authenticate() method.");
		String hql = "from User where id = '" + id + "' and " + "password = '" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
