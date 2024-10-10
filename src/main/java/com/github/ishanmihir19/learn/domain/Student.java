package com.github.ishanmihir19.learn.domain;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Student implements Serializable {

	public Student() {
	}

	public Student(String name, String email, int age, String dept, int score) {
		this.name = name;
		this.email = email;
		this.age = age;
		this.dept = dept;
		this.score = score;
	}

	private static final long serialVersionUID = -1152779434213289790L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private int age;

	@Column
	private Date bday;

	@Column
	private String dept;

	@Column
	private int score;

}
