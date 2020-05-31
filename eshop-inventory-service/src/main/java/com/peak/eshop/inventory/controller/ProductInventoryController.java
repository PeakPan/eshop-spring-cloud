package com.peak.eshop.inventory.controller;

import com.peak.eshop.inventory.model.ProductInventory;
import com.peak.eshop.inventory.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-inventory")
public class ProductInventoryController {

	@Autowired
	private ProductInventoryService productInventoryService;

	@RequestMapping("/add")
	@ResponseBody
	public String add(ProductInventory productInventory) {
		try {
			productInventoryService.add(productInventory);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(ProductInventory productInventory) {
		try {
			productInventoryService.update(productInventory);
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
			productInventoryService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@RequestMapping("/findById")
	@ResponseBody
	public ProductInventory findById(Long id){
		try {
			return productInventoryService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ProductInventory();
	}

	@RequestMapping("/findByProductId")
	@ResponseBody
	public ProductInventory findByProductId(Long productId){
		try {
			return productInventoryService.findByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ProductInventory();
	}

}
