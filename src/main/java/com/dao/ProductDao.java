package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Product;
import com.repo.ProductJpa;

@Repository
public class ProductDao {

	@Autowired
	ProductJpa jpa;
	
	
	public Product saveProduct(Product p)
	{
		return jpa.save(p);
	}
	
	public List<Product> getAllProducts()
	{
		return jpa.findAll();
	}
	
	public Optional<Product> findProductById(int id)
	{
		return jpa.findById(id);
	}
}
