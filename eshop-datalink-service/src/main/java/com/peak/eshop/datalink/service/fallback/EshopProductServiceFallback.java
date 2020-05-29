package com.peak.eshop.datalink.service.fallback;

import com.peak.eshop.datalink.service.EshopProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EshopProductServiceFallback implements EshopProductService {
    @Override
    public String findBrandById(Long id) {
        log.warn("fallback, {} ", id);
        return null;
    }

    @Override
    public String findBrandByIds(String ids) {
        log.warn("fallback, {} ", ids);
        return null;
    }

    @Override
    public String findCategoryById(Long id) {
        log.warn("fallback, {} ", id);
        return null;
    }

    @Override
    public String findProductIntroById(Long id) {
        log.warn("fallback, {} ", id);
        return null;
    }

    @Override
    public String findProductPropertyById(Long id) {
        log.warn("fallback, {} ", id);
        return null;
    }

    @Override
    public String findProductPropertyByProductId(Long productId) {
        log.warn("fallback, {} ", productId);
        return null;
    }

    @Override
    public String findProductById(Long id) {
        log.warn("fallback, {} ", id);
        return null;
    }

    @Override
    public String findProductSpecificationById(Long id) {
        log.warn("fallback, {} ", id);
        return null;
    }

    @Override
    public String findProductSpecificationByProductId(Long productId) {
        log.warn("fallback, {} ", productId);
        return null;
    }
}
