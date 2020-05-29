package com.peak.eshop.product.mapper;

import com.peak.eshop.product.model.ProductIntro;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductIntroMapper {
	
	@Insert("INSERT INTO product_intro(content,product_id) VALUES(#{content},#{productId})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void add(ProductIntro productIntro);
	
	@Update("UPDATE product_intro SET content=#{content},product_id=#{productId} WHERE id=#{id}")
	public void update(ProductIntro productIntro);
	
	@Delete("DELETE FROM product_intro WHERE id=#{id}")
	public void delete(Long id);
	
	@Select("SELECT * FROM product_intro WHERE id=#{id}")
	@Results ({
		@Result(column = "product_id", property = "productId")
	})
	public ProductIntro findById(Long id);

	@Select("SELECT * FROM product_intro WHERE id in (${ids})")
	@Results ({
			@Result(column = "product_id", property = "productId")
	})
	public List<ProductIntro> findByIds(@Param(value = "ids") String ids);

	@Select("SELECT * FROM product_intro WHERE product_id=#{productId}")
	@Results ({
			@Result(column = "product_id", property = "productId")
	})
	public ProductIntro findByProductId(@Param(value = "productId") Long productId);
}