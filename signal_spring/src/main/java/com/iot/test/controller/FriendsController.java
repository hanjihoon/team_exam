package com.iot.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.test.mapper.FriendsMapper;
import com.iot.test.mapper.UserInfoMapper;
import com.iot.test.vo.UserInfoVO;

@Controller
@RequestMapping("/friends")
public class FriendsController {
	
	@Autowired
	FriendsMapper fm;
	
	@Autowired
	UserInfoMapper uim;
	
	@RequestMapping(value="/add")
	public @ResponseBody Map<String,Object> addFriends(@RequestBody Map<String,String> nickNameMap, HttpSession hs) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("msg", "친구등록에 실패하였습니다.");
		resultMap.put("biz", false);
		
		String fId;
		if(nickNameMap.get("uiNickName")!=null) {
			fId = uim.selectUiIdForChat(nickNameMap.get("uiNickName"));		
		}else {			
			fId = nickNameMap.get("uiId");
		}
		Map<String,Object> fMap = new HashMap<String,Object>();
		fMap.put("fId", fId);
		fMap.put("uiId", ((UserInfoVO) hs.getAttribute("user")).getUiId());
		List<Map<String,Object>> friendsCheckList = fm.selectFriendsListCheck(fMap);
		if(friendsCheckList.size()==0) {
			Map<String,Object> fInfoMap = uim.selectUserInfoToFriend(fId);
			fInfoMap.put("uiId", fMap.get("uiId"));
			int result = fm.insertFriends(fInfoMap);
			if(result==1) {	
				List<Map<String,Object>> friendsTargetList = fm.selectFriendsListByUiIdAsFId(((UserInfoVO) hs.getAttribute("user")).getUiId());			
				List<Map<String,Object>> acceptList = new ArrayList<Map<String,Object>>();
				for(int i=0; i<friendsTargetList.size(); i++) {
					String myId = (String) friendsTargetList.get(i).get("fId");
					String otherId = (String) friendsTargetList.get(i).get("uiId");
					Map<String,Object> fMap2 = new HashMap<String,Object>();
					fMap2.put("uiId", myId);
					fMap2.put("fId", otherId);	
					if(fm.selectFriendsListCheck(fMap2).size()!=1) {					
						acceptList.add(friendsTargetList.get(i));					
					}
				}
				hs.setAttribute("acceptSize", acceptList.size());
				resultMap.put("msg", "친구등록이 완료되었습니다.");
				resultMap.put("biz", true);	
			}
		}else {				
			resultMap.put("msg", "이미 등록된 친구입니다.");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/reject")
	public @ResponseBody Map<String,Object> rejectFriends(@RequestBody Map<String,Object> fIdMap, HttpSession hs) {
		
		fIdMap.put("fId", ((UserInfoVO) hs.getAttribute("user")).getUiId());
		int result = fm.deleteFriends(fIdMap);
		
		fIdMap.put("msg", "실패하였습니다.");
		fIdMap.put("biz", false);
		if(result==1) {
			fIdMap.put("msg", "친구요청을 거절하였습니다.");
			fIdMap.put("biz", true);
		}
		return fIdMap;
	}
	
	@RequestMapping(value="/remove")
	public @ResponseBody Map<String,Object> removeFriends(@RequestBody Map<String,Object> uiIdMap, HttpSession hs) {
		uiIdMap.put("uiId", ((UserInfoVO) hs.getAttribute("user")).getUiId());
		int result = fm.deleteFriends(uiIdMap);
		uiIdMap.put("msg", "실패하였습니다.");
		uiIdMap.put("biz", false);
		if(result==1) {
			uiIdMap.put("msg", "친구를 삭제하였습니다.");
			uiIdMap.put("biz", true);
		}		
		return uiIdMap;
	}

}
