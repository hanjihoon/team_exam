package com.iot.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FriendsMapper {
	
	@Select("select * from Friends where uiId=#{uiId} and fId=#{fId}")
	List<Map<String,Object>> selectFriendsListCheck(Map<String,Object> fMap); 
	
	@Select("select * from Friends where uiId=#{uiId}")
	List<Map<String, Object>> selectFriendsListByUiId(String uiId);
	
	@Select("select * from Friends where FId=#{uiId}")
	List<Map<String, Object>> selectFriendsListByUiIdAsFId(String uiId);
	
	@Insert("insert into Friends(fName,fId,uiId) values(#{fName},#{fId},#{uiId})")
	int insertFriends(Map<String,Object> fInfoMap);
	
	@Delete("delete from Friends where fId=#{fId} and uiId=#{uiId}")
	int deleteFriends(Map<String,Object> fIdMap);
	
	
	

}
