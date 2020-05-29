package com.peak.eshop.product.mapper;

import com.peak.eshop.product.model.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CategoryMapper {
	
	@Insert("INSERT INTO category(name,description) VALUES(#{name},#{description})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void add(Category category);
	
	@Update("UPDATE category SET name=#{name},description=#{description} WHERE id=#{id}")  
	public void update(Category category);
	
	@Delete("DELETE FROM category WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM category WHERE id=#{id}")  
	public Category findById(Long id);

	@Select("SELECT * FROM category WHERE id in (${ids})")
	public List<Category> findByIds(@Param(value = "ids") String ids);
	
}