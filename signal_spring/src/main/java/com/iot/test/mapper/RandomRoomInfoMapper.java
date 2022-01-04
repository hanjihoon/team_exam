package com.iot.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RandomRoomInfoMapper {
	
	@Select("call p_searchWatingPeople(#{rRSize})")
	List<Map<String,Object>> selectWatingPeopleList(Map<String,Object> checkMap);
	
	@Select("select * from random_room_info where rRName=#{rRName}")
	List<Map<String,Object>> selectRandomRoomInfo(Map<String,Object> checkMap);
	
	@Select("select uiNickName from random_room_info where rRName=#{rRName}")
	List<String> selectuiNickNameList(Map<String,Object> rMap);
	
	@Insert("insert into random_room_info(rRSize,rRName,uiNickName) "
			+ " values(#{rRSize},#{rRName},#{uiNickName})")
	int insertRandomRoomInfo(Map<String,Object> checkMap);
	
	@Delete("delete from random_room_info where uiNickName=#{uiNickName}")
	int deleteRandomRoomInfo(Map<String,Object> checkMap);

}
