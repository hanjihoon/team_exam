package com.iot.test.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.test.mapper.UserInRoomMapper;
import com.iot.test.service.RoomInfoService;

@Controller
@RequestMapping("/room")
public class RoomInfoController {
	private static final Logger log = LoggerFactory.getLogger(RoomInfoController.class);
	
	@Autowired
	RoomInfoService ris;
	
	@Autowired
	UserInRoomMapper uirm;
	
	
	@RequestMapping(value="/check", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> checkRoomName(@RequestBody Map<String,Object> rNameMap){
		
		ris.setRoomInfo(rNameMap);	
		
		return rNameMap;
	}
	
	@RequestMapping(value="/all", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> allRoom(@RequestBody Map<String,Object> regeonMap){
		
		List<Map<String, Object>> allRoomList = ris.searchAllRoom(regeonMap);		
		
		for(Map<String, Object> subRoomMap : allRoomList) {
			int result = uirm.selectUserInRoomCount(subRoomMap);
			subRoomMap.put("currentAttendee", result);
		}
		
		return allRoomList;
	}
	
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> searchRoom(@RequestBody Map<String,Object> roomMap){
		
		log.info("roomMap =>{}", roomMap);		
		
		List categoryList = (List) roomMap.get("categoryList");		
		if(categoryList.size()==0) {
			List<Map<String, Object>> allRoomList = ris.searchAllRoom(roomMap);			
			for(Map<String, Object> subRoomMap : allRoomList) {
				int result = uirm.selectUserInRoomCount(subRoomMap);
				subRoomMap.put("currentAttendee", result);
			}
			return allRoomList;
		}
		
		List<Map<String, Object>> roomInfoMap = ris.searchRoomInfo(roomMap);
		for(Map<String, Object> subRoomMap : roomInfoMap) {
			int result = uirm.selectUserInRoomCount(subRoomMap);
			subRoomMap.put("currentAttendee", result);
		}			
		return roomInfoMap;	
		
	}
}
