package com.niit.collaboration.controller;

import java.util.List;

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

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;

@RestController
public class JobController {
	Logger log = Logger.getLogger(JobController.class);
	
	@Autowired
	JobDAO jobDAO;
	@GetMapping(value = "/jobs")
	public ResponseEntity<List<Job>> listJobs() {
		log.debug("**********Starting of listJobs() method.");
		List<Job> job = jobDAO.list();
		if(job.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listJobs() method.");
		return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
	}
	@PostMapping(value = "/job/")
	public ResponseEntity<Job> createJob(@RequestBody Job job) {
		log.debug("**********Starting of createJob() method.");
		if(jobDAO.get(job.getId()) == null) {
			jobDAO.save(job);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		log.error("No job exist with id : " + job.getId());
		job.setErrMessage("Job already exist with id : " +job.getId());
		log.debug("**********End of createJob() method.");
		return new ResponseEntity<Job>(HttpStatus.OK);

	}
	@PutMapping(value = "/job/{id}")
	public ResponseEntity<Job> updateJob(@PathVariable("id") String id, @RequestBody Job job) {
		log.debug("**********Starting of updateJob() method.");
		if(jobDAO.get(id) == null) {
			job = new Job();
			job.setErrMessage("No job exist with id : " +job.getId());
			log.error("No job exist with id : " + job.getId());
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDAO.update(job);
		log.debug("**********End of updateJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	@DeleteMapping(value = "/job/{id}")
	public ResponseEntity<Job> deleteJob(@PathVariable("id") String id) {
		log.debug("**********Starting of deleteJob() method.");
		Job job = jobDAO.get(id);
		if(job == null) {
			job = new Job();
			job.setErrMessage("No job exist with id : " + id);
			log.error("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDAO.delete(job);
		log.debug("**********End of deleteJob() method.");
		return new ResponseEntity<Job>(HttpStatus.OK);		
	}
	@GetMapping(value = "/job/{id}")
	public ResponseEntity<Job> getJob(@PathVariable("id") String id) {
		log.debug("**********Starting of getJob() method.");
		Job job = jobDAO.get(id);
		if(job == null) {
			job = new Job();
			job.setErrMessage("No job exist with id : " + id);
			log.error("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
}
