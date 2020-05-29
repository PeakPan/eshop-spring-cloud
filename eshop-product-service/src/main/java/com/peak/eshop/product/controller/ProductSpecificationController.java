package com.peak.eshop.product.controller;

import com.peak.eshop.product.model.ProductProperty;
import com.peak.eshop.product.model.ProductSpecification;
import com.peak.eshop.product.service.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product-specification")
public class ProductSpecificationController {

	@Autowired
	private ProductSpecificationService productSpecificationService;
	
	@RequestMapping("/add") 
	@ResponseBody
	public String add(ProductSpecification productSpecification) {
		try {
			productSpecificationService.add(productSpecification);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/update") 
	@ResponseBody
	public String update(ProductSpecification productSpecification) {
		try {
			productSpecificationService.update(productSpecification); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/delete") 
	@ResponseBody
	public String delete(Long id) {
		try {
			productSpecificationService.delete(id); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/findById") 
	@ResponseBody
	public ProductSpecification findById(Long id){
		try {
			return productSpecificationService.findById(id);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ProductSpecification();
	}

	@RequestMapping("/findByProductId")
	@ResponseBody
	public ProductSpecification findByProductId(Long productId){
		try {
			return productSpecificationService.findByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ProductSpecification();
	}

	@RequestMapping("/findByIds")
	@ResponseBody
	public List<ProductSpecification> findByIds(String ids){
		try {
			return productSpecificationService.findByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}
}
