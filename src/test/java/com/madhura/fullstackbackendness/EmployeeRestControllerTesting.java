package com.madhura.fullstackbackendness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhura.fullstackbackendness.entity.Employee;
import com.madhura.fullstackbackendness.exception.ResourceNotFoundException;
import com.madhura.fullstackbackendness.repository.EmployeeRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@RunWith(SpringRunner.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class EmployeeRestControllerTesting {
	
	@MockBean
	public EmployeeRepository employeeRepository;

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	ObjectMapper objectmapper=new ObjectMapper();
	private List<Employee> mocklistemployees;
	private Employee createEmployee=new Employee();
	private Employee updateEmployee;
	private Employee deleteEmployee;
	@BeforeEach
	public void setUp()
	{
		
		 mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		 Employee empoyee1=new Employee(123,"john","joh","john@gmail.com");
			Employee empoyee2=new Employee(123,"vaishu","vah","vais@gmail.com");
			Employee empoyee3=new Employee(123,"pallavi","pa","pallavi@gmail.com");
	mocklistemployees=new ArrayList<>(Arrays.asList(empoyee1,empoyee2,empoyee3));
	
	createEmployee.setFirstName("rada");
	createEmployee.setLastName("madh");
	createEmployee.setEmailId("radha@gmail.com");
	
	updateEmployee=new Employee(456,"gokual","fa","gokul@gmail.com");
	
	deleteEmployee=new Employee(456,"gokual","fa","gokul@gmail.com");
	}
	
	
	@Test
	public void getEmployees() throws Exception
	{
		
		when(employeeRepository.findAll()).thenReturn(mocklistemployees);
		MvcResult mvcresult=mockMvc.perform(get("/api/v1/employees").
				contentType(MediaType.APPLICATION_JSON_VALUE)).
				andExpect(status().isOk()).andExpect
				(jsonPath("$[0].firstName",is("john"))).andReturn();
		int status=mvcresult.getResponse().getStatus();
		assertEquals(200,status);
		String JsonListEmployees=mvcresult.getResponse().getContentAsString();
		List<Employee> employeelist = objectmapper.readValue(JsonListEmployees, new TypeReference<List<Employee>>(){});
	//assertequals(7,employeelist.size());
		assertTrue(employeelist.size()>0);
	}
	

	@Test
	public void createEmployee() throws Exception
	{
		when(employeeRepository.save(createEmployee)).thenReturn(createEmployee);
	String Employee=objectmapper.writeValueAsString(createEmployee);
	MvcResult mvcResult=mockMvc.perform(post("/api/v1/employees").
			content(Employee).contentType(MediaType.APPLICATION_JSON_VALUE)).
			andExpect(status().isOk()).andExpect
			(jsonPath("$.emailId",is("radha@gmail.com"))).andReturn();
		String resultedEmployee=mvcResult.getResponse().getContentAsString();		
		Employee savedEmployee=objectmapper.readValue(resultedEmployee,Employee.class);
		createEmployee.setId(savedEmployee.getId());
		assertEquals(createEmployee,savedEmployee);
	}
	@Test
	public void getEmployeeById() throws Exception
	{
		Long id=4L;
		Optional<Employee> employee=Optional.ofNullable(new Employee(4L,"john","david","john@gmail.com"));
		when(employeeRepository.findById(id)).thenReturn(employee);
		MvcResult mvcresult=mockMvc.perform(get("/api/v1/employees/"+id).
				contentType(MediaType.APPLICATION_JSON_VALUE)).
				andExpect(status().isOk()).andExpect
				(jsonPath("$.emailId",is("john@gmail.com"))).
				andReturn();
		int status=mvcresult.getResponse().getStatus();
		assertEquals(200,status);
		String responseEmployee=mvcresult.getResponse().getContentAsString();
		Employee employeeById=objectmapper.readValue(responseEmployee,Employee.class);
		assertEquals(id,employeeById.getId());
    }
		
	
	@Test
	public void updateEmployee() throws Exception
	{
		
		Long id=4L;
		
		Optional<Employee> employee=Optional.ofNullable(updateEmployee);
		when(employeeRepository.findById(id)).thenReturn(employee);
		MvcResult mvcResult=mockMvc.perform(get("/api/v1/employees/"+id).
				contentType(MediaType.APPLICATION_JSON_VALUE)).
				andExpect(status().isOk()).andReturn();
	String employeeById=mvcResult.getResponse().getContentAsString();
	Employee saveEmployee=objectmapper.readValue(employeeById,Employee.class);
	saveEmployee.setEmailId("Geetha@gmail.com");
		String jsonSavedEmployee=objectmapper.writeValueAsString(saveEmployee);
		when(employeeRepository.save(saveEmployee)).thenReturn(saveEmployee);
		MvcResult updatemvcResult=mockMvc.perform(put("/api/v1/employees/"+id).
				content(jsonSavedEmployee).
				contentType(MediaType.APPLICATION_JSON_VALUE)).
				andExpect(status().isOk()).andReturn();
		String responseString=updatemvcResult.getResponse().getContentAsString();
		Employee ChangedEmployee=objectmapper.readValue(responseString,Employee.class);
		assertEquals("Geetha@gmail.com",ChangedEmployee.getEmailId());
		
	}
	@Test
	public void deleteEmployee() throws Exception
	{
		Long id=38L;
		
		Optional<Employee> employee=Optional.ofNullable(deleteEmployee);
		when(employeeRepository.findById(id)).thenReturn(employee);
	doNothing().when(employeeRepository).deleteById(id);
		MvcResult mvcResult=mockMvc.perform(delete("/api/v1/employees/"+id).
				contentType(MediaType.APPLICATION_JSON_VALUE)).
				andExpect(status().isOk()).andReturn();
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		
	}
	@Test
	public void employeeNotFoundException () throws Exception
	{
		Long id=4L;
		mockMvc.perform(get("/api/v1/employees/"+id).
	contentType(MediaType.APPLICATION_JSON_VALUE)).
	andExpect(status().isNotFound()).andExpect
	(result->assertTrue(result.getResolvedException() instanceof 
			ResourceNotFoundException)).andExpect(
	result->assertEquals("Employee not found with id "+id,
			result.getResolvedException().getMessage()));
		
		
	}

}
