package com.springbootdevops.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootdevops.model.Employee;
import com.springbootdevops.services.EmployeeService;

//Rest application dem
@RestController
@RequestMapping("/employee")
public class RestMainController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping(value = "/Login")
	public String loginPage() {
		return "loginPage";
	}
	
	@GetMapping(value = "/welcome/{name}")
	public String WelcomeMessage(@PathVariable String name) {
		return "Welcome " + name;
	}

	@GetMapping(value = "/welcome")
	public String Welcome(@RequestParam("name")String name) {
		return "Welcome " + name;
	}
	
	@PostMapping(value = "/saveEmployeeUsingParam")
	public String saveEmployeeUsingParam(@RequestParam(name = "name" ,required =false )String name, @RequestParam(name="address",required = false)String address) {
		Employee emp= new Employee();
		emp.setEmpName(name);
		emp.setEmpAddress(address);
		service.saveEmployeeDetails(emp);
		return "Saved Successfully";
	}
	
	@PostMapping(value = "/saveEmployees")
	public ResponseEntity<String> saveEmployees(@RequestBody Employee emp,BindingResult errorResult) {
		
		if(errorResult.hasErrors()) {
			return new ResponseEntity<>("Insertion Failed",HttpStatus.BAD_REQUEST);
		}
		if(service.saveEmployeeDetails( emp)) {
			return new ResponseEntity<>("Employee Object saved successfully",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("Employee Object failed to save",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/getEmployees")
	public ResponseEntity<List<Employee>> getEmployees(){
		List<Employee> employeeList = new ArrayList<>();
		service.getEmployees().forEach(employeeList::add);
		//service.fetchData().forEach(login::add);
		if(employeeList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteEmployee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
		if(service.deleteEmployee(id))
			return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
		return new ResponseEntity<>("Delete Operation Failed",HttpStatus.FORBIDDEN);
	}
	
	@PutMapping(value = "/updateEmployee")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp) {
		System.out.println(emp);
		if(service.updateEmployee(emp))
			return new ResponseEntity<>("Updated Successfully",HttpStatus.ACCEPTED);
		
		return new ResponseEntity<>("Updation Failed",HttpStatus.BAD_REQUEST);
	}
}



