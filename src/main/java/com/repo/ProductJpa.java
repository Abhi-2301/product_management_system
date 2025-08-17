package com.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Product;


public interface ProductJpa extends JpaRepository<Product,Integer> {

	 List<Product> findByName(String name);
	 
	 List<Product> findByPriceBetween(double min,double max);
	 
}
