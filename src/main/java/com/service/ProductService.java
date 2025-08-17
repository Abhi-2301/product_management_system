package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
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
		rs.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.OK);
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
	
	public ResponseEntity<ResponseStructure<Product>> deleteProductById(int id)
	{
		ResponseStructure<Product> rs=new ResponseStructure<>();
		Optional<Product> p = dao.findProductById(id);
		if(p.isPresent())
		{
			dao.deleteProductById(id);
			rs.setData(p.get());
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data deleted suceessfully");
			rs.setStatusCode(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.FOUND);
		}
		else
		{	
			rs.setData(null);
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data not found");
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.NOT_FOUND);
		}
		
	}
	public ResponseEntity<ResponseStructure<Product>> updateProductById(int id,Product p )
	{
		ResponseStructure rs = new ResponseStructure<>();
		Optional<Product> p1 = dao.findProductById(id);
		if(p1.isPresent())
		{
			Product p2 = dao.updateProductById(id, p);
			rs.setData(p2);
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data updated successfully");
			rs.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.OK);
		}
		else
		{
			rs.setData(null);
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data not found");
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<ResponseStructure<Product>> patchProductById(int id,Product p)
	{
		ResponseStructure rs = new ResponseStructure<>();
		Optional<Product> e = dao.findProductById(id);
		if(e.isPresent())
		{
			 Product product = e.get();
		     if (p.getName() != null) product.setName(p.getName());
		     if (p.getP_desc() != null) product.setP_desc(p.getP_desc());
		     if (p.getPrice() != 0) product.setPrice(p.getPrice());
		     dao.saveProduct(product);
		     rs.setData(product);
		     rs.setLocaldatetime(LocalDateTime.now());
		     rs.setMessage("product partially updated successfully (PATCH)");
		     rs.setStatusCode(HttpStatus.OK.value());
		     
		        return new ResponseEntity<>(rs, HttpStatus.OK);

		}
		else
		{
			rs.setData(null);
	        rs.setLocaldatetime(LocalDateTime.now());
	        rs.setMessage("Product not found");
	        rs.setStatusCode(HttpStatus.NOT_FOUND.value());

	        return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findProductByName(String name)
	{
		ResponseStructure<List<Product>> rs=new ResponseStructure<>();
		List<Product> p = dao.findByName(name);
		if(!p.isEmpty())
		{
			rs.setData(p);
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data fetched successfully");
			rs.setStatusCode(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Product>>>(rs,HttpStatus.FOUND);
		}
		else
		{
			rs.setData(null);
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data not found");
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());

			return new ResponseEntity<ResponseStructure<List<Product>>>(rs,HttpStatus.NOT_FOUND);
		}
		
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findByPriceBetween(double min,double max)
	{
		ResponseStructure<List<Product>> rs=new ResponseStructure<>();
		List<Product> p = dao.findByPriceBetween(min, max);
		if(!p.isEmpty())
		{
			rs.setData(p);
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data fetched ");
			rs.setStatusCode(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Product>>>(rs,HttpStatus.FOUND);
		}
		else
		{
			rs.setData(null);
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data not found");
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Product>>>(rs,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByProductPage(int page,int size,String sortBy,boolean ascending)
	{
		ResponseStructure<List<Product>> rs = new ResponseStructure<>();
		
		Sort sort=ascending? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		PageRequest pageable = PageRequest.of(page, size, sort);

		List<Product> products = dao.findByProductPage(pageable);
		if(!products.isEmpty())
		{
			rs.setData(products);
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data found successfully");
			rs.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<List<Product>>>(rs,HttpStatus.OK);
		}
		else
		{
			rs.setData(null);
			rs.setLocaldatetime(LocalDateTime.now());
			rs.setMessage("data not found");
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Product>>>(rs,HttpStatus.NOT_FOUND);
		}
	}
		
}
	
