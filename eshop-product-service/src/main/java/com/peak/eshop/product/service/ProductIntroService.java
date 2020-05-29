package com.peak.eshop.product.service;

import com.peak.eshop.product.model.ProductIntro;

import java.util.List;

public interface ProductIntroService {
	
	public void add(ProductIntro productIntro);
	
	public void update(ProductIntro productIntro);
	
	public void delete(Long id);
	
	public ProductIntro findById(Long id);

    List<ProductIntro> findByIds(String ids);

    ProductIntro findByProductId(Long productId);
}