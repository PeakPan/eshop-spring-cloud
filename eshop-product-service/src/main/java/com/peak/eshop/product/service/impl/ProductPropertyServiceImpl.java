package com.peak.eshop.product.service.impl;

import com.peak.eshop.product.mapper.ProductPropertyMapper;
import com.peak.eshop.product.model.ProductProperty;
import com.peak.eshop.product.rabbitmq.RabbitMQSender;
import com.peak.eshop.product.rabbitmq.RabbitQueue;
import com.peak.eshop.product.service.ProductPropertyService;
import com.peak.eshop.product.uitl.JsonBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPropertyServiceImpl implements ProductPropertyService {

	@Autowired
	private ProductPropertyMapper productPropertyMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(ProductProperty productProperty) {
		productPropertyMapper.add(productProperty);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("add", "productProperty", productProperty.getId()));
	}

	public void update(ProductProperty productProperty) {
		productPropertyMapper.update(productProperty);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("update", "productProperty", productProperty.getId()));
	}

	public void delete(Long id) {
		productPropertyMapper.delete(id);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("del", "productProperty", id));
	}

	public ProductProperty findById(Long id) {
		return productPropertyMapper.findById(id);
	}

	@Override
	public List<ProductProperty> findByIds(String ids) {
		return productPropertyMapper.findByIds(ids);
	}

	@Override
	public ProductProperty findByProductId(Long productId) {return productPropertyMapper.findByProductId(productId); }

}