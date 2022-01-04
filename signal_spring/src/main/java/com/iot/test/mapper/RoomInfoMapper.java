package com.iot.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.iot.test.vo.UserInRoomVO;

@Mapper
public interface RoomInfoMapper {
	
	List<Map<String,Object>> selectRoomInfoList(Map<String,Object> rNameMap);
	int insertRoomInfo(Map<String,Object> rMap);
	
	@Select("select rName, rSize, rTag, iconName from room_info where regeonNo=#{regeonNo}")
	List<Map<String, Object>> selectAllRoomList(Map<String,Object> regeonNo);
	
	@Select("select rName, rSize, rTag, iconName from room_info where categoryNo=#{categoryNo} and regeonNo=#{regeonNo}")
	List<Map<String, Object>> selectRoomList(Map<String,Object> roomInfo);
	
	@Select("select rSize from room_info where rName=#{rName}")
	int selectRSizeForRName(Map<String,Object> rMap);
	
		
	@Delete("delete from room_info where rName=#{rName}")
	int deleteRoomInfo(UserInRoomVO uirv);
	
	
	
}
