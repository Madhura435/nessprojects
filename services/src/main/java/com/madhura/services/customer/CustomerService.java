package com.madhura.services.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhura.entity.customer.Customer;
import com.madhura.repositary.customer.CustomerRepositary;

@Service
public class CustomerService {
@Autowired(required=true)
private CustomerRepositary customerRepositary;
public Customer saveCustomer(Customer customer)
{
	return customerRepositary.save(customer);
}
public List<Customer> listofCustomers()
{
	return customerRepositary.findAll();
}

}
