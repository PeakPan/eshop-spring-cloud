package com.peak.eshop.inventory.model;

import lombok.Data;

@Data
public class ProductInventory {
	private Long id;
	private Integer value;
	private Long productId;
}