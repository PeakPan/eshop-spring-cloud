package com.peak.eshop.product.service.impl;

import com.peak.eshop.product.mapper.BrandMapper;
import com.peak.eshop.product.model.Brand;
import com.peak.eshop.product.rabbitmq.RabbitMQSender;
import com.peak.eshop.product.rabbitmq.RabbitQueue;
import com.peak.eshop.product.service.BrandService;
import com.peak.eshop.product.uitl.JsonBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    RabbitMQSender rabbitMQSender;

    public void add(Brand brand) {
        brandMapper.add(brand);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("add", "brand", brand.getId()));
    }

    public void update(Brand brand) {
        brandMapper.update(brand);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("update", "brand", brand.getId()));
    }

    public void delete(Long id) {
        brandMapper.delete(id);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, JsonBuildUtils.buildRabbitmqMsg("del", "brand", id));
    }

    public Brand findById(Long id) {
        return brandMapper.findById(id);
    }

    @Override
    public List<Brand> findByIds(String ids) {
        return brandMapper.findByIds(ids);
    }
}
