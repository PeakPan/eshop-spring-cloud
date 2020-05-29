package com.peak.eshop.product.service.impl;

import com.peak.eshop.product.mapper.ProductIntroMapper;
import com.peak.eshop.product.model.ProductIntro;
import com.peak.eshop.product.rabbitmq.RabbitMQSender;
import com.peak.eshop.product.rabbitmq.RabbitQueue;
import com.peak.eshop.product.service.ProductIntroService;
import com.peak.eshop.product.uitl.JsonBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductIntroServiceImpl implements ProductIntroService {

	@Autowired
	private ProductIntroMapper productIntroMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(ProductIntro productIntro) {
		productIntroMapper.add(productIntro);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("add", "productIntro", productIntro.getId()));
	}

	public void update(ProductIntro productIntro) {
		productIntroMapper.update(productIntro);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("update", "productIntro", productIntro.getId()));
	}

	public void delete(Long id) {
		productIntroMapper.delete(id);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("del", "productIntro", id));
	}

	public ProductIntro findById(Long id) {
		return productIntroMapper.findById(id);
	}

	@Override
	public List<ProductIntro> findByIds(String ids) {
		return productIntroMapper.findByIds(ids);
	}

	@Override
	public ProductIntro findByProductId(Long productId) {
		return productIntroMapper.findByProductId(productId);
	}
}