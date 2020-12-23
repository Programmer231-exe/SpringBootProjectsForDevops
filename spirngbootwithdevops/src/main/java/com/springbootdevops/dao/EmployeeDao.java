package com.springbootdevops.dao;

import java.util.List;

import com.springbootdevops.model.Employee;

public interface EmployeeDao {
	boolean saveEmployeeDetails(Employee emp);
	List<Employee> getEmployees();
	boolean deleteEmployee(int id);
	boolean updateEmployee(Employee emp);
}
