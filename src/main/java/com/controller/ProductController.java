package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Product;
import com.service.ProductService;
import com.util.ResponseStructure;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts()
	{
		return service.getAllProducts();
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable("id") int id)
	{
		return service.findProductById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Product>> create(@RequestBody Product p)
	{
		return service.saveProduct(p);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Product>> deleteProductById(@PathVariable("id") int id)
	{
		return service.deleteProductById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Product>> updateProductById(@PathVariable("id") int id,@RequestBody Product p)
	{
		return service.updateProductById(id, p);
	}
	
	@PatchMapping("/patch/{id}")
	public ResponseEntity<ResponseStructure<Product>> patchProductById(@PathVariable("id") int id,@RequestBody Product p)
	{
		return service.patchProductById(id,p);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<ResponseStructure<List<Product>>> findProductByName(@PathVariable("name") String name)
	{
		return service.findProductByName(name);
	}
	
	@GetMapping("/price/{min}/{max}")
	public ResponseEntity<ResponseStructure<List<Product>>> findProductByPriceBetween(@PathVariable("min") double min,@PathVariable("max") double max)
	{
		return service.findByPriceBetween(min, max);
	}
	
	@GetMapping("/getProductPage/{page}/{size}/{sortBy}/{ascending}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByProductPage(@PathVariable("page") int page,@PathVariable("size") int size,@PathVariable("sortBy") String sortBy,@PathVariable("ascending") boolean ascending)
	{
		return service.findByProductPage(page,size, sortBy, ascending);
	}
}
