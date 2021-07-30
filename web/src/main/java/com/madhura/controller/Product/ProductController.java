package com.madhura.controller.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.madhura.entity.customer.Customer;
import com.madhura.services.customer.CustomerService;
import com.madhura.entity.product.Product;
import com.madhura.services.product.*;

@RestController
public class ProductController {
	@Autowired
	public ProductService productService;
	@PostMapping("/product")
	public Product createProduct(@RequestBody Product product)
	{
		return productService.saveProduct(product);
	}
	@GetMapping("/products")
	public List<Product> getProducts()
	{
		return productService.listofProducts();
	}
}
