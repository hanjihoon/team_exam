package com.iot.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {	
	
	List<Map<String,Object>> selectCategoryList();
	
	@Select("select categoryNo from category where categoryName=#{categoryName}")
	Map<String,Object> selectCategoryNo(Map<String,Object> categoryName);

}
