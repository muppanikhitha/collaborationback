
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

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;

@RestController
public class UserController {

	Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	UserDAO userDAO;
	
	@GetMapping(value = "/user")
	public ResponseEntity<List<User>> listUser() {
		log.debug("**********Starting of listUser() method.");
		List<User> user = userDAO.list();
		if(user.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listUser() method.");
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/user/")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		log.debug("**********Starting of createUser() method.");
		if(userDAO.get(user.getId()) == null) {
			userDAO.save(user);
			log.debug("**********End of createUser() method.");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		user.setErrMessage("User already exist with id : " +user.getId());
		log.error("User already exist with id : " +user.getId());
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	 
	@PutMapping(value = "/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
		log.debug("**********Starting of updateUser() method.");
		if(userDAO.get(id) == null) {
			user = new User();
			user.setErrMessage("User does not exist with id : " +user.getId());
			log.error("User does not exist with id : " +user.getId());
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		userDAO.update(user);
		log.debug("**********End of updateUser() method.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
		log.debug("**********Starting of deleteUser() method.");
		User user = userDAO.get(id);
		if(user == null) {
			user = new User();
			user.setErrMessage("User does not exist with id : " + id);
			log.error("User does not exist with id : " + id);
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		userDAO.delete(user);
		log.debug("**********End of deleteUser() method.");
		return new ResponseEntity<User>(HttpStatus.OK);		
	}
	
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		log.debug("**********Starting of getUser() method.");
		User user = userDAO.get(id);
		if(user == null) {
			user = new User();
			user.setErrMessage("User does not exist with id : " + id);
			log.error("User does not exist with id : " + id);
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getUser() method.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	 
	@PostMapping(value = "/user/authenticate/")
	public ResponseEntity<User> authenticateUser(@RequestBody User user, HttpSession session) {
		log.debug("**********Starting of authenticateUser() method.");
		user = userDAO.authenticate(user.getId(), user.getPassword());
		if(user == null) {
			user = new User();
			user.setErrMessage("Invalid userId or password...");
			log.error("Invalid userId or password...");
		}
		else {
			session.setAttribute("loggedInUser", user);
			session.setAttribute("loggedInUserID", user.getId());
		}
		log.debug("**********End of authenticateUser() method.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}


