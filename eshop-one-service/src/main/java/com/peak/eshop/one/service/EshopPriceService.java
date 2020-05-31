package com.peak.eshop.one.service;

import com.peak.eshop.one.service.fallback.EshopPriceFallbackService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eshop-price-service", fallback = EshopPriceFallbackService.class)
public interface EshopPriceService {

    @RequestMapping(value = "/product-price/findByProductId", method = RequestMethod.GET)
    String findByProductId(@RequestParam(value = "productId") Long productId);
}
