package com.iot.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ColorInfoMapper {
	
	List<Map<String,Object>> selectColorList();
	
	@Select("select categoryName from color_info where colorId = #{colorId}")
	Map<String,Object> selectCategoryName(String colorId);

}
