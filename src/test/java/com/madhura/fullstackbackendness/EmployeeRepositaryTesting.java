package com.madhura.fullstackbackendness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.madhura.fullstackbackendness.entity.Employee;
import com.madhura.fullstackbackendness.repository.EmployeeRepository;
import com.madhura.fullstackbackendness.service.EmployeeService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EmployeeRepositaryTesting {

	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@Order(2)
	public void getEmployees()
	{
		List<Employee> employeelist=new ArrayList<Employee>();
		employeelist=employeeRepository.findAll();
		assertThat(employeelist).size().isGreaterThan(0);
		
	}
	
	@Test
	@Order(1)
	public void createEmployee()
	{
		Employee employee=new Employee();
		employee.setFirstName("pallavi");
		employee.setLastName("kupalla");
		employee.setEmailId("pa@gmail.com");
		employeeRepository.save(employee);
		//assertNotNull(ery.findById(2L).get());
		Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}
	@Test
	@Order(3)
	public void getEmployeeById()
	{
		Employee employee=employeeRepository.findById(2L).get();
		assertEquals(2L,employee.getId());
	}
	
	@Test
	@Order(4)
	public void updateEmployee()
	{
		Employee employee=employeeRepository.findById(2L).get();
		employee.setEmailId("madhura199@gmail.com");
		employeeRepository.save(employee);
		assertNotEquals("m@gmail.com",employeeRepository.findById(2L).get().getEmailId());
	}
	
	@Test
	@Order(5)
	public void deleteEmployee()
	{
		employeeRepository.deleteById(2L);
		assertThat(employeeRepository.existsById(2L)).isFalse();
	}
	
	
}
