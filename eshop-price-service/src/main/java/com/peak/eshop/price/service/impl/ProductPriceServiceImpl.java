package com.peak.eshop.price.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.peak.eshop.price.mapper.ProductPriceMapper;
import com.peak.eshop.price.model.ProductPrice;
import com.peak.eshop.price.service.ProductPriceService;
import com.peak.eshop.price.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

	@Autowired
	private ProductPriceMapper productPriceMapper;

	@Autowired
	private RedisUtil redisUtil;

	public void add(ProductPrice productPrice) {
		productPriceMapper.add(productPrice);
		redisUtil.set("product_price_" +  productPrice.getId(), JSONUtils.toJSONString(productPrice));
	}

	public void update(ProductPrice productPrice) {
		productPriceMapper.update(productPrice);
		redisUtil.set("product_price_" +  productPrice.getId(), JSONUtils.toJSONString(productPrice));
	}

	public void delete(Long id) {
		productPriceMapper.delete(id);
		redisUtil.del("product_price_" +  id);
	}

	public ProductPrice findById(Long id) {
		return productPriceMapper.findById(id);
	}

}
