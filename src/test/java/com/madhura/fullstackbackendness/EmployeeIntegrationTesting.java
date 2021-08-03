package com.madhura.fullstackbackendness;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.madhura.fullstackbackendness.entity.Employee;
import com.madhura.fullstackbackendness.exception.ResourceNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FullstackBackendNessApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeIntegrationTesting {

	@LocalServerPort
	private int port;
	TestRestTemplate testRestTemplate=new TestRestTemplate();
	HttpHeaders httpHeaders=new HttpHeaders();
	Map<String,String> haspmap=new HashMap();
	@Test
	public void getEmployees() throws Exception
	{
		String url = "http://localhost:"+port+"/api/v1/employees/";
		ResponseEntity<List> responseEntity=testRestTemplate.getForEntity
				(url,List.class);
		List<Employee> listEmployee=responseEntity.getBody();
		Assertions.assertNotNull(responseEntity);
	//	Assertions.assertEquals(28,ae.size());
		assertThat(listEmployee).size().isGreaterThan(0);
		Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
	}
	
	@Test
	public void createEmployee() throws Exception
	{
		String url="http://localhost:"+port+"/api/v1/employees/";
		Employee employee=new Employee(789,"prakurthi","pra","pra@gmail.com");
		ResponseEntity<Employee> responseEntity=testRestTemplate.postForEntity(url,employee,Employee.class);
		Employee savedEmployee=responseEntity.getBody();
		Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
		employee.setId(savedEmployee.getId());
		Assertions.assertEquals(employee,savedEmployee);
	}
	
	@Test
	public void getEmployeeById() throws Exception
	{
		long id=3L;
		String url="http://localhost:"+port+
				"/api/v1/employees/{id}";
		ResponseEntity<Employee> responseEntity=testRestTemplate.getForEntity
				(url,Employee.class,id);
		Employee employeeById=responseEntity.getBody();
		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
		Assertions.assertEquals(3L,employeeById.getId());
	}
	
	@Test
	public void updateEmployee() throws Exception
	{
		long id=3L;
		String geturl="http://localhost:"+port+
				"/api/v1/employees/{id}";
		ResponseEntity<Employee> employeebyId=testRestTemplate.getForEntity
				(geturl,Employee.class,id);
		Employee updateEmployee=employeebyId.getBody();
		updateEmployee.setFirstName("kruthika");
		updateEmployee.setLastName("ka");
		updateEmployee.setEmailId("kruthika@gmail.com");
		String updateUrl="http://localhost:"+port+"/api/v1/employees/";
		ResponseEntity<Employee> responseEntity=testRestTemplate.postForEntity(updateUrl,updateEmployee,Employee.class);
		Employee changedEmployee=responseEntity.getBody();
		Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
		updateEmployee.setId(changedEmployee.getId());
		Assertions.assertEquals(updateEmployee,changedEmployee);	
		
	}
	@Test
	public void deleteEmployee() throws Exception
	{
		Long id=34L;
		String url="http://localhost:"+port+
				"/api/v1/employees/{id}";
		ResponseEntity<Void> responseEntity=testRestTemplate.exchange
				(url,HttpMethod.DELETE,null,Void.class,id);
		Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
	}
	
	

}




