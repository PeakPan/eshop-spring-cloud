package com.peak.eshop.product.service.impl;

import com.peak.eshop.product.mapper.CategoryMapper;
import com.peak.eshop.product.model.Category;
import com.peak.eshop.product.rabbitmq.RabbitMQSender;
import com.peak.eshop.product.rabbitmq.RabbitQueue;
import com.peak.eshop.product.service.CategoryService;
import com.peak.eshop.product.uitl.JsonBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(Category category) {
		categoryMapper.add(category);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("add", "category", category.getId()));
	}

	public void update(Category category) {
		categoryMapper.update(category);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("update", "category", category.getId()));
	}

	public void delete(Long id) {
		categoryMapper.delete(id);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("del", "category", id));
	}

	public Category findById(Long id) {
		return categoryMapper.findById(id);
	}

	@Override
	public List<Category> findByIds(String ids) {
		return categoryMapper.findByIds(ids);
	}


}
