package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Product;


public interface ProductJpa extends JpaRepository<Product,Integer> {

}
