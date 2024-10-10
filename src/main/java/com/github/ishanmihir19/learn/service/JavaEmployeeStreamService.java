package com.github.ishanmihir19.learn.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.ishanmihir19.learn.domain.Employee;

@Service
public class JavaEmployeeStreamService {

	static List<Employee> empList;
	static {
		empList = new ArrayList<>();
		empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));
		empList.add(new Employee(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));
		empList.add(new Employee(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));
		empList.add(new Employee(4, "def", 32, 125, "F", "HR", "Chennai", 2013));
		empList.add(new Employee(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));
		empList.add(new Employee(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));
		empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));
		empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));
		empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));
	}


	/**
	 * Q1.) Group the Employees by city.
	 */
	private void q1() {
		Map<String, List<Employee>> empByCity;
		empByCity = empList.stream().collect(Collectors.groupingBy(Employee::getCity));
		System.out.println("1.) Employees grouped by city :: \n" + empByCity);
	}

	/**
	 * Q2.) Group the Employees by age.
	 */
	private void q2() {
		Map<Integer, List<Employee>> empByAge = empList.stream().collect(Collectors.groupingBy(Employee::getAge));
		System.out.println("2.) Employees grouped by age :: \n" + empByAge);
	}

	/**
	 * Q3.) Find the count of male and female employees present in the organization.
	 */
	private void q3() {
		Map<String, Long> noOfMaleAndFemaleEmployees = empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(
				"3.) Count of male and female employees present in the organization:: \n" + noOfMaleAndFemaleEmployees);
	}

	/**
	 * Q4.) Print the names of all departments in the organization.
	 */
	private void q4() {
		System.out.println("4.) Names of all departments in the organization ");
		empList.stream().map(Employee::getDeptName).distinct().forEach(System.out::println);
	}

	/**
	 * Q5.) Print employee details whose age is greater than 28.
	 */
	private void q5() {
		System.out.println("5.) Employee details whose age is greater than 28");
		empList.stream().filter(e -> e.getAge() > 28).toList().forEach(System.out::println);
	}

	/**
	 * Q6.) Find maximum age of employee.
	 */
	private void q6() {
		OptionalInt max = empList.stream().mapToInt(Employee::getAge).max();
		if (max.isPresent())
			System.out.println("6.) Maximum age of Employee: " + max.getAsInt());
	}

	/**
	 * Q7.) Print Average age of Male and Female Employees.
	 */
	private void q7() {
		Map<String, Double> avgAge = empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println("7.) Average age of Male and Female Employees:: " + avgAge);
	}

	/**
	 * Q8.) Print the number of employees in each department.
	 */
	private void q8() {
		Map<String, Long> countByDept = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
		System.out.println("8.) No of employees in each department");
		for (Map.Entry<String, Long> entry : countByDept.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

	/**
	 * Q9.) Find oldest employee.
	 */
	private void q9() {
		Optional<Employee> oldestEmp = empList.stream().max(Comparator.comparingInt(Employee::getAge));
		Employee oldestEmployee = oldestEmp.get();
		System.out.println("9.) Oldest employee details:: \n" + oldestEmployee);
	}

	/**
	 * Q10.) Find youngest female employee.
	 */
	private void q10() {
		System.out.println("10.) " + empList.stream().filter(s -> s.getGender().equals("F")).min(Comparator.comparingInt(Employee::getAge)).get().getName()
				+ " has the lowest age in female employees...");
	}
	
	/**
	 * Q11.) Find employees whose age is greater than 30 and less than 30. (Partitioning)
	 */
	private void q11() {
		Map<Boolean, List<Employee>> partitioned = empList.stream().collect(Collectors.partitioningBy(e->e.getAge()>30));
		System.out.println("11.) ");
		partitioned.forEach((key, value) -> {
			if (Boolean.TRUE.equals(key)) {
				System.out.println("Employees above 30 years of age are : ");
				value.forEach(z -> {
					System.out.print(z.getName() + ", ");
				});
			} else {
				System.out.println("Employees less than 30 years of age are : ");
				value.forEach(z -> {
					System.out.print(z.getName() + ", ");
				});
			}
		});
	}

	/**
	 * Q12.) Find the department name which has the highest number of employees.
	 */
	private void q12() {
		Map<String, Long> collect = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
		Entry<String, Long> max = Collections.max(collect.entrySet(),Comparator.comparingLong(Map.Entry::getValue));
		System.out.println("12.) Department with maximum employees is : " + max.getKey());
	}
	
	/**
	 * Q13.) Find if there any employees from HR Department.
	 */
	private void q13() {
		System.out.println("13.) HR Department has employees : " + empList.stream().anyMatch(e -> e.getDeptName().equalsIgnoreCase("HR")));
	}

	/**
	 * Q14.) Find the department names that these employees work for, where the number of employees in the department is over 3.
	 */
	private void q14() {
		System.out.println("14.)  All departments with more than 3 employees are : " );
		empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet().stream().filter(x->x.getValue()>3).forEach(y->System.out.println(y.getKey()));
	}

	/**
	 * Q15.) Find distinct department names that employees work for.
	 */
	private void q15() {
		System.out.println("15.) Distinct Departments are : " );
		empList.stream().map(Employee::getDeptName).distinct().forEach(System.out::println);
	}

	public String startStreamsQuestions() {

		System.out.println("******* Starting Employee Stream Service Questions ********");
		q1();
		q2();
		q3();
		q4();
		q5();
		q6();
		q7();
		q8();
		q9();
		q10();
		q11();
		q12();
		q13();
		q14();
		q15();
		return "All Done!";
	}

}
