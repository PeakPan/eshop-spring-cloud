package com.peak.eshop.product.service.impl;

import com.peak.eshop.product.mapper.ProductMapper;
import com.peak.eshop.product.model.Product;
import com.peak.eshop.product.rabbitmq.RabbitMQSender;
import com.peak.eshop.product.rabbitmq.RabbitQueue;
import com.peak.eshop.product.service.ProductService;
import com.peak.eshop.product.uitl.JsonBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(Product product) {
		productMapper.add(product);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("add", "product", product.getId()));
	}

	public void update(Product product) {
		productMapper.update(product);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("update", "product", product.getId()));
	}

	public void delete(Long id) {
		productMapper.delete(id);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("del", "product", id));
	}

	public Product findById(Long id) {
		return productMapper.findById(id);
	}

	@Override
	public List<Product> findByIds(String ids) {
		return productMapper.findByIds(ids);
	}

}