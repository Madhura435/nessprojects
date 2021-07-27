package com.madhura.fullstackbackendness.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhura.fullstackbackendness.entity.Employee;
import com.madhura.fullstackbackendness.exception.ResourceNotFoundException;
import com.madhura.fullstackbackendness.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepositary;
	
	public List<Employee> getlistEmployees()
	{try
	{
		return employeeRepositary.findAll();	
	}
	catch(RuntimeException e)
	{
		throw new ResourceNotFoundException("Employee not found with id ");
	}
		
	}

	public Employee saveEmployee(Employee employee)
	{
		return employeeRepositary.save(employee);
	}
	
	public Optional<Employee> returnEmployeeById
	(Long id)
	{
		Optional<Employee> empoyeebyid=employeeRepositary.findById(id);
		return empoyeebyid;
		
	}
	
	public Employee changeEmployee(Employee employee)
	{
		return employeeRepositary.save(employee);
	}
	
	public void removeEmployee(long id)
	{	
		employeeRepositary.deleteById(id);	
	}

	
}
