package com.peak.eshop.product.service;

import com.peak.eshop.product.model.ProductSpecification;

import java.util.List;

public interface ProductSpecificationService {
	
	public void add(ProductSpecification productSpecification);
	
	public void update(ProductSpecification productSpecification);
	
	public void delete(Long id);
	
	public ProductSpecification findById(Long id);

    List<ProductSpecification> findByIds(String ids);

	ProductSpecification findByProductId(Long productId);
}