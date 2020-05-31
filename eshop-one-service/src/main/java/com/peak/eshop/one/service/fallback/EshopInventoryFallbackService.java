package com.peak.eshop.one.service.fallback;

import com.peak.eshop.one.service.EshopInventoryService;
import com.peak.eshop.one.service.EshopPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EshopInventoryFallbackService implements EshopInventoryService {
    @Override
    public String findByProductId(Long productId) {
        log.error("fallback : {}", productId);
        return null;
    }
}
