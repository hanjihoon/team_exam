package com.iot.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegeonMapper {
	
	List<Map<String,Object>> selectRegeonList();	
	Map<String,Object> selectRegeonNo(String regeonName);
	

}
