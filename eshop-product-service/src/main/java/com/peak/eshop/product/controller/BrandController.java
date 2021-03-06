package com.peak.eshop.product.controller;

import com.peak.eshop.product.model.Brand;
import com.peak.eshop.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@RequestMapping("/add")
	public String add(Brand brand) {
		try {
			brandService.add(brand);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/update")
	public String update(Brand brand) {
		try {
			brandService.update(brand); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/delete")
	public String delete(Long id) {
		try {
			brandService.delete(id); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/findById")
	public Brand findById(Long id){
		try {
			return brandService.findById(id);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new Brand();
	}


	@RequestMapping("/findByIds")
	public List<Brand> findByIds(String ids){
		try {
			return brandService.findByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}