package com.madhura.controller.Customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhura.emailService.EmailService;
import com.madhura.entity.customer.Customer;
import com.madhura.services.customer.CustomerService;

@RestController
public class CustomerController {
	/*
	 {
	 "id":1,
	 "name":"john",
	 "email":"john@gmail.com",
	 "phoneNo":818181818
	 }
	 */
	@Autowired
	public CustomerService customerService;
	@Autowired
	public EmailService emailService;
	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer customer)
	{
		emailService.sendEmailtoall();
		return customerService.saveCustomer(customer);
	}
	@GetMapping("/customers")
	public List<Customer> getCustomers()
	{
		return customerService.listofCustomers();
	}
	
	
}
