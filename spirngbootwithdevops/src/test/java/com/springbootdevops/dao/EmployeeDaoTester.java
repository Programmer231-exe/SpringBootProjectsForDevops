package com.springbootdevops.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springbootdevops.model.Employee;

@SpringBootTest
public class EmployeeDaoTester {

	@Autowired
	EmployeeDao dao;
	
	private Employee goodEmployeeObject;
	private Employee falseEmployeeObject;
	
	@BeforeEach
	void beforeEachTest() {
		goodEmployeeObject = new Employee(23,"Sivasankar S","Bharath");
		falseEmployeeObject = new Employee();
	}
	
	
	@Test
	void employeeSaveConfigCheck() {
		assertTrue(dao.saveEmployeeDetails(goodEmployeeObject));
	}
	
	@Test
	void employeeSaveConfigFalseData() {
		assertFalse(dao.saveEmployeeDetails(falseEmployeeObject));
	}
	
	@Test
	void updateEmployeeWithLegitData() {
		assertTrue(dao.updateEmployee(goodEmployeeObject));
	}
	
	@Test
	void updateEmployeeWithFalseData() {
		assertFalse(dao.updateEmployee(falseEmployeeObject));
	}
	
	@Test
	void getEmployeesCheck() {
		dao.getEmployees().forEach(e -> assertNotNull(e));
	}
	
	@AfterEach
	void afterEachTest() {
		goodEmployeeObject = null;
		falseEmployeeObject = null;
	}
	}
