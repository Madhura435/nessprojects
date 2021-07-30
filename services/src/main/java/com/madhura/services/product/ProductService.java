package com.madhura.services.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhura.entity.customer.Customer;
import com.madhura.entity.product.Product;
import com.madhura.repositary.product.ProductRepositary;
@Service
public class ProductService {
	@Autowired
	public ProductRepositary productRepository;
	public Product saveProduct(Product product)
	{
		return productRepository.save(product);
	}
	public List<Product> listofProducts()
	{
		return productRepository.findAll();
	}

}
