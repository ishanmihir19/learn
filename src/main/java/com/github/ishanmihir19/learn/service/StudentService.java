package com.github.ishanmihir19.learn.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ishanmihir19.learn.domain.Student;
import com.github.ishanmihir19.learn.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public Student saveStudent(Student s) {
		return studentRepository.save(s);
	}

	public List<Student> fetchAll() {
		return studentRepository.findAll();
	}

	public void deleteStudent(int id) {
		studentRepository.deleteById(id);
	}

	public void resetStudentTable() {

		studentRepository.deleteAll();

		List<Student> allStudents = Arrays.asList(new Student("Jasmine Kaur", "jm@jmx.com", 23, "CSE", 79),
				new Student("Karan Jindal", "kj@apple.com", 22, "EEE", 65),
				new Student("Sherry Kaur", "sk@uiet.com", 25, "ECE", 30),
				new Student("Siddharth Malhotra", "sid@iitk.com", 23, "CSE", 89));
		studentRepository.saveAll(allStudents);
	}

}
