package com.madhura.repositary.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madhura.entity.customer.Customer;
import com.madhura.entity.product.Product;

@Repository
public interface ProductRepositary extends JpaRepository<Product,Long> {

}
