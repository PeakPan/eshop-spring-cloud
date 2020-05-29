package com.peak.eshop.product.service;

import com.peak.eshop.product.model.ProductProperty;

import java.util.List;

public interface ProductPropertyService {
	
	public void add(ProductProperty productProperty);
	
	public void update(ProductProperty productProperty);
	
	public void delete(Long id);
	
	public ProductProperty findById(Long id);

    List<ProductProperty> findByIds(String ids);

    ProductProperty findByProductId(Long productId);
}