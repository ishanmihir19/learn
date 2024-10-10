package com.github.ishanmihir19.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.ishanmihir19.learn.domain.Student;
import com.github.ishanmihir19.learn.service.StudentService;

@RestController
public class StudentResource {

	@Autowired
	StudentService studentService;

	@PostMapping("/student")
	public Student saveStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}

	@GetMapping("/student")
	public List<Student> fetchAllStudents() {
		return studentService.fetchAll();
	}

	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
	}

	@GetMapping("/student/reset")
	public void resetStudentTable() {
		studentService.resetStudentTable();
	}

}
