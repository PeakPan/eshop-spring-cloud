package com.peak.eshop.product.controller;

import com.peak.eshop.product.model.ProductIntro;
import com.peak.eshop.product.model.ProductProperty;
import com.peak.eshop.product.service.ProductPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product-property")
public class ProductPropertyController {

	@Autowired
	private ProductPropertyService productPropertyService;
	
	@RequestMapping("/add") 
	@ResponseBody
	public String add(ProductProperty productProperty) {
		try {
			productPropertyService.add(productProperty);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/update") 
	@ResponseBody
	public String update(ProductProperty productProperty) {
		try {
			productPropertyService.update(productProperty); 
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
			productPropertyService.delete(id); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/findById") 
	@ResponseBody
	public ProductProperty findById(Long id){
		try {
			return productPropertyService.findById(id);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ProductProperty();
	}

	@RequestMapping("/findByProductId")
	@ResponseBody
	public ProductProperty findByProductId(Long productId){
		try {
			return productPropertyService.findByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ProductProperty();
	}

	@RequestMapping("/findByIds")
	@ResponseBody
	public List<ProductProperty> findByIds(String ids){
		try {
			return productPropertyService.findByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
}
