package com.peak.eshop.product.service;

import com.peak.eshop.product.model.Brand;

import java.util.List;

public interface BrandService {
    public void add(Brand brand);

    public void update(Brand brand);

    public void delete(Long id);

    public Brand findById(Long id);

    List<Brand> findByIds(String ids);
}
