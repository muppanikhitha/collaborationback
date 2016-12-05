package com.niit.collaboration.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.ChatForumDAO;
import com.niit.collaboration.model.ChatForum;
import com.niit.collaboration.model.ChatForumComment;


@EnableTransactionManagement
@Repository(value="chatforumDAO")
public class ChatForumDAOImpl implements ChatForumDAO {
	
	Logger log = Logger.getLogger(ChatForumDAOImpl.class);
	
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	/**
	 *  getter/setter method for sessionFactory
	 */	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Constructor of ChatChatForumDAOImpl...
	 */
	public ChatForumDAOImpl() { 		
		
	}	
	public ChatForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */
	
	@Transactional
	public boolean save(ChatForum chatforum){				//working
		try {
			log.debug("**********Starting of save() method.");
			chatforum.setCreatedDate(new Date(System.currentTimeMillis()));
			
			sessionFactory.getCurrentSession().save(chatforum);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(ChatForum chatforum){				//working
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(chatforum);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(ChatForum chatforum) {				//working
		try {
			log.debug("**********Starting of delete() method.");
			sessionFactory.getCurrentSession().delete(chatforum);
			log.debug("**********End of delete() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public ChatForum get(int id) {								//working
		log.debug("**********Starting of get() method.");
		String hql = "from ChatForum where id = '" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<ChatForum> list = query.list();
		
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
	public List<ChatForum> list() {									//working
		log.debug("**********Starting of list() method.");
		String hql = " from ChatForum ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of list() method.");
		return query.list();
	}
	
	@Transactional
	public boolean saveComment(ChatForumComment chatforumComment) {
		try {
			log.debug("**********Starting of saveComment() method.");
			chatforumComment.setCommentedDate(new Date(System.currentTimeMillis()));
			
			sessionFactory.getCurrentSession().save(chatforumComment);
			log.debug("**********End of saveComment() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")				//working
	@Transactional
	public List<ChatForumComment> listComment(String  id) {
		log.debug("**********Starting of listComment() method.");
		int fid=Integer.parseInt(id);
		String hql = " from ChatForumComment where chatforumId = "+ id ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of listComment() method.");
		return query.list();
	}
	
	@Transactional
	public ChatForumComment getComment(int id) {						//working
		log.debug("**********Starting of getComment() method.");
		String hql = "from ChatForumComment where id = '" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<ChatForumComment> list = query.list();
		
		if(list != null && !list.isEmpty()) {
			log.debug("**********End of getComment() method.");
			return list.get(0);
		}
		else {
			return null;
		}
	}	
}

