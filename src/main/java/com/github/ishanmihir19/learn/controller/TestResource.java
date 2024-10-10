package com.github.ishanmihir19.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ishanmihir19.learn.service.JavaEmployeeStreamService;
import com.github.ishanmihir19.learn.service.JavaStreamService;

@RestController
public class TestResource {

	@Autowired
	JavaStreamService javaStreamService;
	
	@Autowired
	JavaEmployeeStreamService javaEmployeeStreamService;
	
	

	@GetMapping("/test")
	public void fetchAllStudents() {
		javaStreamService.test();
	}
	
	/**
	 * https://medium.com/@harendrakumarrajpoot5/java8-stream-api-commonly-asked-interview-questions-answer-9ab7a6800ff3
	 */
	@GetMapping("/emp/streams")
	public String employeeStreamPlayAround() {
		return javaEmployeeStreamService.startStreamsQuestions();
	}

}
