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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	public EmployeeService employeeService;
	@ApiOperation(value="get list of employees",notes="By giving this url we get the list of employees",
			response=Employee.class,responseContainer="List")
	@GetMapping("/getEmployees")
	public List<Employee> getEmployees()
	{
		return employeeService.getlistEmployees();
	}
	
	@ApiOperation(value="create employee in database",notes="provide json object we can create employee",
			response=Employee.class)
	@PostMapping("/saveEmployee")
	public Employee createEmployee(@ApiParam(value="provide json object for craeting employee") 
	@RequestBody Employee employee)
	{
		return employeeService.saveEmployee(employee);
	}
	
	@ApiOperation(value="get Employee by Id",notes="provide the id we get specific employee by Id",
			response=Employee.class)
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Employee> getEmployeeById
	(@ApiParam
			(value="Id value for get employee",required=true) @PathVariable Long id)
	{
		Employee empoyeebyid=employeeService.returnEmployeeById(id)
				.orElseThrow(() ->
		new ResourceNotFoundException("Employee not found with id "+id));
		return ResponseEntity.ok(empoyeebyid);
	}
	
	
	@ApiOperation(value="update the employee",notes="provide id,json employee object we can update the employee",
			response=Employee.class)
	@PutMapping("/updateEmployees/{id}")
	public ResponseEntity<Employee> updateEmployee(@ApiParam
			(value="Id value for update employee",required=true) @PathVariable Long id,@ApiParam(value="provide json object for updating employee") @RequestBody Employee updateemployee)
	{
		Employee employeebyId=employeeService.returnEmployeeById(id).orElseThrow(() ->new ResourceNotFoundException("Employee not found with id "+id));
		employeebyId.setFirstName(updateemployee.getFirstName());
		employeebyId.setLastName(updateemployee.getLastName());
		employeebyId.setEmailId(updateemployee.getEmailId());
		Employee changedEmployee=employeeService.changeEmployee(employeebyId);
		return ResponseEntity.ok(changedEmployee);
	}
	
	
	@ApiOperation(value="delete employee by Id",notes="provide id we can delete the employee by id")
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@ApiParam
			(value="Id value for delete employee",required=true) @PathVariable long id)
	{
		Employee employee=employeeService.returnEmployeeById(id).orElseThrow(() ->new ResourceNotFoundException("Employee not found with id "+id));
		employeeService.removeEmployee(id);
		Map<String,Boolean> response=new HashMap();
		response.put("Deleted employee wih id "+id,Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}



