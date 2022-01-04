package com.iot.test.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.test.mapper.UserInRoomMapper;
import com.iot.test.service.UserInRoomService;
import com.iot.test.vo.UserInRoomVO;

@Service
public class UserInRoomServiceImpl implements UserInRoomService{
	
	@Autowired
	UserInRoomMapper uirm;

	@Override
	public int insertUserInRoom(Map<String, Object> rMap) {
		
		UserInRoomVO uirv = new UserInRoomVO();
		uirv.setrName((String) rMap.get("rName")); 
		uirv.setUiId((String) rMap.get("uiId"));				
		return uirm.insertUserInRoom(uirv);
	}

	


}
