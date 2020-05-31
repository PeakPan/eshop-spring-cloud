package com.peak.eshop.one.service.fallback;

import com.peak.eshop.one.service.EshopPriceService;
import org.springframework.stereotype.Component;

@Component
public class EshopPriceFallbackService implements EshopPriceService {
    @Override
    public String findByProductId(Long productId) {
        return "error fallback";
    }
}
