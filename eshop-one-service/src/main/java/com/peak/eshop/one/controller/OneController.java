package com.peak.eshop.one.controller;

import com.peak.eshop.one.service.EshopInventoryService;
import com.peak.eshop.one.service.EshopPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/one")
public class OneController {

    @Autowired
    private EshopInventoryService eshopInventoryService;

    @Autowired
    private EshopPriceService eshopPriceService;

    @GetMapping(value = "findInventoryByProductId")
    public String findInventoryByProductId(Long productId) {
        return eshopInventoryService.findByProductId(productId);
    }

    @GetMapping(value = "findPriceByProductId")
    public String findPriceByProductId(Long productId) {
        return eshopPriceService.findByProductId(productId);
    }
}
