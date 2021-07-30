package com.madhura.repositary.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madhura.entity.customer.Customer;

@Repository
public interface CustomerRepositary extends JpaRepository<Customer,Long>{

}
