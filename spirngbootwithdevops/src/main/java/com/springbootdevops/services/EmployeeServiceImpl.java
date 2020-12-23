package com.springbootdevops.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootdevops.dao.EmployeeDao;
import com.springbootdevops.model.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeDao dao;
	
	public boolean saveEmployeeDetails(Employee emp) {
		return dao.saveEmployeeDetails(emp);
	}
	
	public List<Employee> getEmployees(){
		return dao.getEmployees();
	}
	
	public boolean deleteEmployee(int id) {
		return dao.deleteEmployee(id);
	}
	
	public boolean updateEmployee(Employee emp) {
		return dao.updateEmployee(emp);
	}
}
