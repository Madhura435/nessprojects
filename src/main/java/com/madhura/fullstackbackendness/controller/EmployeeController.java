package com.madhura.fullstackbackendness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhura.fullstackbackendness.entity.Employee;
import com.madhura.fullstackbackendness.exception.ResourceNotFoundException;
import com.madhura.fullstackbackendness.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	public EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees()
	{
		return employeeService.getlistEmployees();
	}
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeService.saveEmployee(employee);
	}
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById
	(@PathVariable Long id)
	{
		Employee empoyeebyid=employeeService.returnEmployeeById(id)
				.orElseThrow(() ->
		new ResourceNotFoundException("Employee not found with id "+id));
		return ResponseEntity.ok(empoyeebyid);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee updateemployee)
	{
		Employee employeebyId=employeeService.returnEmployeeById(id).orElseThrow(() ->new ResourceNotFoundException("Employee not found with id "+id));
		employeebyId.setFirstName(updateemployee.getFirstName());
		employeebyId.setLastName(updateemployee.getLastName());
		employeebyId.setEmailId(updateemployee.getEmailId());
		Employee changedEmployee=employeeService.changeEmployee(employeebyId);
		return ResponseEntity.ok(changedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable long id)
	{
		Employee employee=employeeService.returnEmployeeById(id).orElseThrow(() ->new ResourceNotFoundException("Employee not found with id "+id));
		employeeService.removeEmployee(id);
		Map<String,Boolean> response=new HashMap();
		response.put("Deleted employee wih id "+id,Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}



