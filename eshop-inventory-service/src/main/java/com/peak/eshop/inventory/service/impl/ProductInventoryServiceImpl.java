package com.peak.eshop.inventory.service.impl;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.peak.eshop.inventory.mapper.ProductInventoryMapper;
import com.peak.eshop.inventory.model.ProductInventory;
import com.peak.eshop.inventory.service.ProductInventoryService;
import com.peak.eshop.inventory.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Autowired
	private ProductInventoryMapper productInventoryMapper;

	@Autowired
	private RedisUtil redisUtil;

	public void add(ProductInventory productInventory) {
		productInventoryMapper.add(productInventory);
		redisUtil.set("product_inventory" + productInventory.getId(), JSONObject.toJSONString(productInventory));
	}

	public void update(ProductInventory productInventory) {
		productInventoryMapper.update(productInventory);
		redisUtil.set("product_inventory" + productInventory.getId(), JSONObject.toJSONString(productInventory));
	}

	public void delete(Long id) {
		productInventoryMapper.delete(id);
		redisUtil.del("product_inventory" + id);
	}

	public ProductInventory findById(Long id) {
		return productInventoryMapper.findById(id);
	}

	@Override
	public ProductInventory findByProductId(Long productId) {
		return productInventoryMapper.findByProductId(productId);
	}

}
