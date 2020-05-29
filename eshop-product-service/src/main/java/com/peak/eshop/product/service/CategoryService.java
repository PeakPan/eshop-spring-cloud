package com.peak.eshop.product.service;

import com.peak.eshop.product.model.Category;

import java.util.List;

public interface CategoryService {
	
	public void add(Category category);
	
	public void update(Category category);
	
	public void delete(Long id);
	
	public Category findById(Long id);

    List<Category> findByIds(String ids);
}