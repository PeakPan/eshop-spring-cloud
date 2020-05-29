package com.peak.eshop.product.model;

import lombok.Data;

@Data
public class ProductSpecification {

	private Long id;
	private String name;
	private String value;
	private Long productId;
}