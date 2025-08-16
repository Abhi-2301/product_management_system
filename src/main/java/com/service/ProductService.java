package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.ProductDao;
import com.entity.Product;
import com.util.ResponseStructure;

@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product p)
	{
		ResponseStructure<Product> rs=new ResponseStructure<>();
		Product p1 = dao.saveProduct(p);
		rs.setData(p1);
		rs.setLocaldatetime(LocalDateTime.now());
		rs.setMessage("Data Inserted Successfully");
		rs.setStatusCode(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.FOUND);
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts()
	{
		ResponseStructure<List<Product>> rs = new ResponseStructure<>();
		List<Product> p = dao.getAllProducts();
		rs.setData(p);
		rs.setLocaldatetime(LocalDateTime.now());
		rs.setMessage("Data fetched successfully");
		rs.setStatusCode(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Product>> findProductById(int id)
	{
		ResponseStructure<Product> rs=new ResponseStructure<>();
		Optional<Product> p1 = dao.findProductById(id);
		if(p1.isPresent())
		{
		rs.setData(p1.get());
		rs.setLocaldatetime(LocalDateTime.now());
		rs.setMessage("data fetched successfully");
		rs.setStatusCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.FOUND);
		}
		else
		{
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data not found");
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.NOT_FOUND);
		}
	}
		
}
	
