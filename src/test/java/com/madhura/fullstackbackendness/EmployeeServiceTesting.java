package com.madhura.fullstackbackendness;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.madhura.fullstackbackendness.entity.Employee;
import com.madhura.fullstackbackendness.exception.ResourceNotFoundException;
import com.madhura.fullstackbackendness.repository.EmployeeRepository;
import com.madhura.fullstackbackendness.service.EmployeeService;

import junit.framework.Assert;

@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeServiceTesting {
	@Autowired
	public EmployeeService employeeService;
	@MockBean
	public EmployeeRepository employeeRepository;
	@Test
	public void getlistEmployees()
	{
		when(employeeRepository.findAll()).thenReturn(Stream.of
				(new Employee(123,"rupa","gar","rupa@gmail.com"),
				new Employee(134,"gothimi","mad","gothimi@gmail.com")).
				collect(Collectors.toList()));
		assertEquals(2,employeeService.getlistEmployees().size());
	}
	@Test
	public void saveEmployee()
	{
		Employee employee=new Employee(87,"john","david","john@gmail.com");
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee,employeeService.saveEmployee(employee));
	}
	@Test
	public void returnEmployeeById()
	{
		Long id=874L;
		Optional<Employee> employee=Optional.ofNullable(new Employee(874,"john","david","john@gmail.com"));
		when(employeeRepository.findById(3L)).thenReturn(employee);
		assertEquals(employee,employeeService.returnEmployeeById(3L));
		
	}
	
	@Test
	public void changeEmployee()
	{
		Employee updateEmployee=new Employee(64,"Rangitha","ga","ra@gmail.com");
		when(employeeRepository.save(updateEmployee)).thenReturn(updateEmployee);
		Employee resultedEmployee=employeeService.changeEmployee(updateEmployee);
		assertNotNull(resultedEmployee);
		assertEquals(updateEmployee,resultedEmployee);
	}
	
	@Test
	public void removeEmployee()
	{
		Employee employee=new Employee(456,"gokual","fa","gokul@gmail.com");
		employeeService.removeEmployee(employee.getId());
		verify(employeeRepository,times(1)).deleteById(employee.getId());
	}
	
}





