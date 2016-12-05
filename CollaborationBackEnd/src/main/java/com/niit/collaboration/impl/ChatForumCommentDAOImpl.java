package com.niit.collaboration.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.ChatForumCommentDAO;
import com.niit.collaboration.model.ChatForumComment;


@EnableTransactionManagement
@Repository(value="chatforumCommentDAO")
public class ChatForumCommentDAOImpl implements ChatForumCommentDAO {
	
	Logger log = Logger.getLogger(ChatForumCommentDAOImpl.class);
	
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	/**  
	 * getter/setter method for sessionFactory
	 */	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *   Constructor of ChatChatForumCommentDAOImpl...	
	 */
	public ChatForumCommentDAOImpl() { 		
		
	}	
	public ChatForumCommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */
	
	@Transactional
	public boolean save(ChatForumComment chatforumComment){
		try {
			log.debug("**********Starting of save() method.");
			sessionFactory.getCurrentSession().save(chatforumComment);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(ChatForumComment chatforumComment){
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(chatforumComment);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(ChatForumComment chatforumComment) {
		try {
			log.debug("**********Starting of saveOrUpdate() method.");
			sessionFactory.getCurrentSession().saveOrUpdate(chatforumComment);
			log.debug("**********End of saveOrUpdate() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(ChatForumComment chatforumComment) {
		try {
			sessionFactory.getCurrentSession().delete(chatforumComment);
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public ChatForumComment get(String id) {
		log.debug("**********Starting of get() method.");
		String hql = "from ChatForumComment where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<ChatForumComment> list = query.list();
		
		if(list != null && !list.isEmpty()) {
			log.debug("**********End of get() method.");
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ChatForumComment> list() {
		log.debug("**********Starting of list() method.");
		String hql = "from ChatForumComment";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of list() method.");
		return query.list();
	}	
}
