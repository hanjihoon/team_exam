package com.iot.test.service;

import java.util.List;
import java.util.Map;

public interface RoomInfoService {
	
	public void setRoomInfo(Map<String,Object> rNameMap);
	public int insertRoomInfo(Map<String,Object> rMap);	
	public List<Map<String, Object>> searchAllRoom(Map<String,Object> regeonMap);
	public List<Map<String, Object>> searchRoomInfo(Map<String,Object> roomMap);
}
