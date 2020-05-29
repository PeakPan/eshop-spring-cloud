package com.peak.eshop.datasync.rabbitmq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.peak.eshop.datasync.service.EshopProductService;
import com.peak.eshop.datasync.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RabbitListener(queues = "data-change-queue")
@Slf4j
public class DataChangeQueueReceiver {  
	
	@Autowired
	private EshopProductService eshopProductService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private RabbitMQSender rabbitMQSender;

	private Set<String> dimMessageSet = Collections.synchronizedSet(new HashSet<>());

	private List<JSONObject> brandDataChangeMessageList = new ArrayList<>();

	public DataChangeQueueReceiver() {
		new SendThread().start();
	}
  
    @RabbitHandler
    public void process(String message) {
    	try {
			// 对这个message进行解析
			log.info("收到msg:{}", message);
			JSONObject jsonObject = JSONObject.parseObject(message);
			// 先获取data_type
			String dataType = jsonObject.getString("data_type");
			if ("brand".equals(dataType)) {
				processBrandDataChangeMessage(jsonObject);
			} else if ("category".equals(dataType)) {
				processCategoryDataChangeMessage(jsonObject);
			} else if ("productIntro".equals(dataType)) {
				processProductIntroDataChangeMessage(jsonObject);
			} else if ("productProperty".equals(dataType)) {
				processProductPropertyDataChangeMessage(jsonObject);
			} else if ("product".equals(dataType)) {
				processProductDataChangeMessage(jsonObject);
			} else if ("productSpecification".equals(dataType)) {
				processProductSpecificationDataChangeMessage(jsonObject);
			}
		} catch (Exception e) {
    		log.error(e.toString());
		}
    }  
    
    private void processBrandDataChangeMessage(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id"); 
    	String eventType = messageJSONObject.getString("event_type"); 
    	
    	if("add".equals(eventType) || "update".equals(eventType)) {
			brandDataChangeMessageList.add(messageJSONObject);
			if(brandDataChangeMessageList.size() > 2) {
				StringBuilder sb = new StringBuilder();
				brandDataChangeMessageList.forEach(msg -> {
					sb.append(",").append(msg.getLong("id"));
				});
				sb.deleteCharAt(0);
				JSONArray dataJSONArray = JSONArray.parseArray(eshopProductService.findBrandByIds(sb.toString()));
				for (int i = 0; i < dataJSONArray.size(); i++) {
					JSONObject dataJSONObject = dataJSONArray.getJSONObject(0);
					log.info("msg:{}", dataJSONObject.toJSONString());
					redisUtil.set("brand_" + dataJSONObject.getLong("id"), dataJSONObject.toJSONString());
					dimMessageSet.add("{\"dim_type\": \"brand\", \"id\": " + id + "}");
				}
				brandDataChangeMessageList.clear();
			}
    	} else if ("delete".equals(eventType)) {
			redisUtil.del("brand_" + id);
			dimMessageSet.add("{\"dim_type\": \"brand\", \"id\": " + id + "}");
    	}
    }
    
    private void processCategoryDataChangeMessage(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id"); 
    	String eventType = messageJSONObject.getString("event_type"); 
    	
    	if("add".equals(eventType) || "update".equals(eventType)) {
			JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findCategoryById(id));
			redisUtil.set("category_" + dataJSONObject.getLong("id"), dataJSONObject.toJSONString());
			log.info("redis 写数据, key={}", "category_" + dataJSONObject.getLong("id"));
    	} else if ("delete".equals(eventType)) {
			redisUtil.del("category_" + id);
    	}

		dimMessageSet.add("{\"dim_type\": \"category\", \"id\": " + id + "}");
    }
    
    private void processProductIntroDataChangeMessage(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id");
		JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findProductIntroById(id));
		Long productId = dataJSONObject.getLong("productId");
    	String eventType = messageJSONObject.getString("event_type"); 
    	
    	if("add".equals(eventType) || "update".equals(eventType)) {
			redisUtil.set("product_intro_" + productId, dataJSONObject.toJSONString());
			log.info("redis 写数据, key={}", "product_intro_" + productId);
    	} else if ("delete".equals(eventType)) {
			redisUtil.del("product_intro_" + productId);
    	}

		dimMessageSet.add("{\"dim_type\": \"product\", \"id\": " + productId + "}");
    }
    
    private void processProductDataChangeMessage(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id"); 
    	String eventType = messageJSONObject.getString("event_type"); 
    	
    	if("add".equals(eventType) || "update".equals(eventType)) { 
    		JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findProductById(id));  
			redisUtil.set("product_" + id, dataJSONObject.toJSONString());
			log.info("redis 写数据, key={}", "product_" + dataJSONObject.getLong("id"));
    	} else if ("delete".equals(eventType)) {
			redisUtil.del("product_" + id);
    	}

		dimMessageSet.add("{\"dim_type\": \"product\", \"id\": " + id + "}");
    }
    
    private void processProductPropertyDataChangeMessage(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id");
		JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findProductPropertyById(id));
		Long productId = dataJSONObject.getLong("productId");
    	String eventType = messageJSONObject.getString("event_type");
    	if("add".equals(eventType) || "update".equals(eventType)) {
			redisUtil.set("product_property_" + productId, dataJSONObject.toJSONString());
			log.info("redis 写数据, key={}", "product_property_" + productId);
    	} else if ("delete".equals(eventType)) {
			redisUtil.del("product_property_" + productId);
    	}

		dimMessageSet.add("{\"dim_type\": \"product\", \"id\": " + productId + "}");
    }
    
    private void processProductSpecificationDataChangeMessage(JSONObject messageJSONObject) {
    	Long id = messageJSONObject.getLong("id");
    	String eventType = messageJSONObject.getString("event_type");
		JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findProductSpecificationById(id));
		Long productId = dataJSONObject.getLong("productId");
    	if("add".equals(eventType) || "update".equals(eventType)) {
			redisUtil.set("product_specification_" + productId, dataJSONObject.toJSONString());
			log.info("redis 写数据, key={}", "product_specification_" + productId);
    	} else if ("delete".equals(eventType)) {
			redisUtil.del("product_specification_" + productId);
    	}

		dimMessageSet.add("{\"dim_type\": \"product\", \"id\": " + productId + "}");
    }

    private class SendThread extends Thread {
    	@Override
    	public void run() {
    		while (true) {
    			try {
					if (!dimMessageSet.isEmpty()) {
						dimMessageSet.forEach(msg -> {
							rabbitMQSender.send("aggr-data-change-queue", msg);
						});
						dimMessageSet.clear();
					}
					Thread.sleep(5000);
				}catch (Exception e) {
    				log.error("{}", e);
				}
			}
		}
	}
}  