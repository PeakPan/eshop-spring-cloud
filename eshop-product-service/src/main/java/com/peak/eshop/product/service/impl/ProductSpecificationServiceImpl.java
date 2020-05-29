package com.peak.eshop.product.service.impl;

import com.peak.eshop.product.mapper.ProductSpecificationMapper;
import com.peak.eshop.product.model.ProductSpecification;
import com.peak.eshop.product.rabbitmq.RabbitMQSender;
import com.peak.eshop.product.rabbitmq.RabbitQueue;
import com.peak.eshop.product.service.ProductSpecificationService;
import com.peak.eshop.product.uitl.JsonBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

	@Autowired
	private ProductSpecificationMapper productSpecificationMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(ProductSpecification productSpecification) {
		productSpecificationMapper.add(productSpecification);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("add", "productSpecification", productSpecification.getId()));
	}

	public void update(ProductSpecification productSpecification) {
		productSpecificationMapper.update(productSpecification);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("update", "productSpecification", productSpecification.getId()));
	}

	public void delete(Long id) {
		productSpecificationMapper.delete(id);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("del", "productSpecification", id));
	}

	public ProductSpecification findById(Long id) {
		return productSpecificationMapper.findById(id);
	}

	@Override
	public List<ProductSpecification> findByIds(String ids) {
		return productSpecificationMapper.findByIds(ids);
	}

	@Override
	public ProductSpecification findByProductId(Long productId) {
		return productSpecificationMapper.findByProductId(productId);
	}

}