package com.peak.eshop.dataaggr.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.peak.eshop.dataaggr.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RabbitListener(queues = "aggr-data-change-queue")
@Slf4j
public class AggrDataChangeQueueReceiver {  
	
	@Autowired
	private RedisUtil redisUtil;
	
    @RabbitHandler
    public void process(String message) {
		try {
			log.info("收到msg:{}", message);
			JSONObject messageJSONObject = JSONObject.parseObject(message);

			String dimType = messageJSONObject.getString("dim_type");

			if ("brand".equals(dimType)) {
				processBrandDimDataChange(messageJSONObject);
			} else if ("category".equals(dimType)) {
				processCategoryDimDataChange(messageJSONObject);
			} else if ("productIntro".equals(dimType)) {
				processProductIntroDimDataChange(messageJSONObject);
			} else if ("product".equals(dimType)) {
				processProductDimDataChange(messageJSONObject);
			}
		}catch (Exception e) {
			log.error("handle fail msg: {}", message);
		}
    }  
    
    private void processBrandDimDataChange(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id");
    	
    	// 多此一举，看一下，查出来一个品牌数据，然后直接就原样写redis
    	// 实际上是这样子的，我们这里是简化了数据结构和业务，实际上任何一个维度数据都不可能只有一个原子数据
    	// 品牌数据，肯定是结构多变的，结构比较复杂，有很多不同的表，不同的原子数据
    	// 实际上这里肯定是要将一个品牌对应的多个原子数据都从redis查询出来，然后聚合之后写入redis
    	String dataJSON = (String) redisUtil.get("brand_" + id);
    	
    	if(dataJSON != null && !"".equals(dataJSON)) {
			redisUtil.set("dim_brand_" + id, dataJSON);
    	} else {
			redisUtil.del("dim_brand_" + id);
    	}
    }
    
    private void processCategoryDimDataChange(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id");
    	
    	// 多此一举，看一下，查出来一个品牌数据，然后直接就原样写redis
    	// 实际上是这样子的，我们这里是简化了数据结构和业务，实际上任何一个维度数据都不可能只有一个原子数据
    	// 品牌数据，肯定是结构多变的，结构比较复杂，有很多不同的表，不同的原子数据
    	// 实际上这里肯定是要将一个品牌对应的多个原子数据都从redis查询出来，然后聚合之后写入redis
    	String dataJSON = (String) redisUtil.get("category_" + id);
    	
    	if(dataJSON != null && !"".equals(dataJSON)) {
			redisUtil.set("dim_category_" + id, dataJSON);
    	} else {
			redisUtil.del("dim_category_" + id);
    	}
    }
    
    private void processProductIntroDimDataChange(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id");
    	
    	String dataJSON = (String) redisUtil.get("product_intro_" + id);
    	
    	if(dataJSON != null && !"".equals(dataJSON)) {
			redisUtil.set("dim_product_intro_" + id, dataJSON);
    	} else {
			redisUtil.del("dim_product_intro_" + id);
    	}
    }
    
    private void processProductDimDataChange(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id");
		List<String> results = redisUtil.mget("product_" + id, "product_property_" + id, "product_specification_" + id);

    	if(results != null && results.size() > 0) {
    		JSONObject productDataJSONObject = JSONObject.parseObject(results.get(0));
    		String productPropertyDataJSON = results.get(1);
    		if(productPropertyDataJSON != null && !"".equals(productPropertyDataJSON)) {
    			productDataJSONObject.put("product_property", JSONObject.parse(productPropertyDataJSON));
    		} 
    		
    		String productSpecificationDataJSON = results.get(2);
    		if(productSpecificationDataJSON != null && !"".equals(productSpecificationDataJSON)) {
    			productDataJSONObject.put("product_specification", JSONObject.parse(productSpecificationDataJSON));
    		}

			redisUtil.set("dim_product_" + id, productDataJSONObject.toJSONString());
    	} else {
			redisUtil.del("dim_product_" + id);
    	}
    }
  
}  