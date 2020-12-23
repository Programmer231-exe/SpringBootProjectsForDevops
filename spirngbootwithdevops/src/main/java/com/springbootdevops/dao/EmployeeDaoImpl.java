package com.springbootdevops.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbootdevops.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean saveEmployeeDetails(Employee emp) {
		
		if(emp.getEmpName() == null || emp.getEmpAddress() == null) {
			return false;
		}
		String sql = "INSERT INTO employee(empName,empAddress) VALUES (?,?)";
		int result = jdbcTemplate.update(sql,"Sivasankar","India");
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<Employee> getEmployees(){
		return jdbcTemplate.query("select * from employee",new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNumber) throws SQLException{
				Employee e = new Employee();
				e.setId(rs.getInt(1));
				e.setEmpAddress(rs.getString("empAddress"));
				e.setEmpName(rs.getString("empName"));
				return e;
			}
		});
	}	
	
	
	public boolean deleteEmployee(int id) {
		
		String sql = "DELETE from employee where empId = ?";
		int result  = jdbcTemplate.update(sql,id);
		if(result > 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean updateEmployee(Employee emp) {
		String sql = "UPDATE employee set empName = ?, empAddress = ? WHERE empId = ?";
		int result = jdbcTemplate.update(sql,emp.getEmpName(),emp.getEmpAddress(),emp.getId());
		if(result > 0) {
			return true;
		}else {
			return false;
		}
		
	}
}
