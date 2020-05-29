package com.peak.eshop.product.service;

import com.peak.eshop.product.model.Product;

import java.util.List;

public interface ProductService {
	
	public void add(Product product);
	
	public void update(Product product);
	
	public void delete(Long id);
	
	public Product findById(Long id);

    List<Product> findByIds(String ids);
}