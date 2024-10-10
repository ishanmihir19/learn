package com.github.ishanmihir19.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ishanmihir19.learn.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
