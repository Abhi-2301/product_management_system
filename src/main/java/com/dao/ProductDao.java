package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Product deleteProductById(int id)
	{
		Optional<Product> e = jpa.findById(id);
		if(e.isPresent())
		{
			Product p = e.get();
			jpa.deleteById(id);
			return p;
		}
		else
		{
			return null;
		}
	}
	
	public Product updateProductById(int id,Product p)
	{
		Optional<Product> p1 = jpa.findById(id);
		if(p1.isPresent())
		{
			Product p2 = p1.get();
			p2.setName(p.getName());
			p2.setP_desc(p.getP_desc());
			p2.setPrice(p.getPrice());
			
			return jpa.save(p2);
		}
		else
		{
			return null;
		}
	}
	public Product patchProductById(int id,Product p)
	{
		Optional<Product> p1 = jpa.findById(id);
		if(p1.isPresent())
		{
			Product p2 = p1.get();
			p2.setName(p.getName());
			p2.setP_desc(p.getP_desc());
			p2.setPrice(p.getPrice());
			
			return jpa.save(p2);
		}
		else
		{
			return null;
		}
	}
	public List<Product> findByName(String name)
	{
		return jpa.findByName(name);
		
		
	}
	public List<Product> findByPriceBetween(double min,double max)
	{
		return jpa.findByPriceBetween(min, max);
	}
	
	public List<Product> findByProductPage(Pageable pageable)
	{
		Page<Product> page = jpa.findAll(pageable);
		return page.getContent();
	}
	
	
}
