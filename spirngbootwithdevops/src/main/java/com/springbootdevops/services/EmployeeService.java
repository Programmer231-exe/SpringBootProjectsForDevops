package com.springbootdevops.services;

import java.util.List;

import com.springbootdevops.model.Employee;

public interface EmployeeService {
	boolean saveEmployeeDetails(Employee emp);
	List<Employee> getEmployees();
	boolean deleteEmployee(int id);
	boolean updateEmployee(Employee emp);
}
