package com.peak.eshop.product.mapper;
import com.peak.eshop.product.model.ProductProperty;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductPropertyMapper {
	
	@Insert("INSERT INTO product_property(name,value,product_id) VALUES(#{name},#{value},#{productId})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void add(ProductProperty productProperty);
	
	@Update("UPDATE product_property SET name=#{name},value=#{value},product_id=#{productId} WHERE id=#{id}")  
	public void update(ProductProperty productProperty);
	
	@Delete("DELETE FROM product_property WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM product_property WHERE id=#{id}")
	@Results ({
			@Result(column = "product_id", property = "productId")
	})
	public ProductProperty findById(Long id);

	@Select("SELECT * FROM product_property WHERE id in (${ids})")
	@Results ({
			@Result(column = "product_id", property = "productId")
	})
	public List<ProductProperty> findByIds(@Param(value = "ids") String ids);

	@Select("SELECT * FROM product_property WHERE product_id=#{productId}")
	@Results ({
			@Result(column = "product_id", property = "productId")
	})
	public ProductProperty findByProductId(@Param(value = "productId") Long productId);
	
}