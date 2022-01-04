package com.iot.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IconInfoMapper {
	
	@Select("select iconNo, iconName, iconCode from icon_info")
	public List<Map<String,Object>> selectIconInfoList();

}
