package com.iot.test.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iot.test.mapper.RandomRoomInfoMapper;
import com.iot.test.vo.UserInfoVO;

@Controller
@RequestMapping("/random")
public class RandomRoomInfoController {	
	private static final Logger log = LoggerFactory.getLogger(RandomRoomInfoController.class);
	
	@Autowired
	private RandomRoomInfoMapper rrim;
	
	@RequestMapping("/chat")
	public ModelAndView goInRandomChat(@RequestParam Map<String,Object> checkMap, HttpSession hs) {
		ModelAndView mav = new ModelAndView();		
		//rrim.deleteRandomRoomInfo(checkMap);
		mav.addObject("rName", checkMap.get("rRName"));
		mav.addObject("uiNickName", checkMap.get("uiNickName"));
		mav.addObject("uiId", ((UserInfoVO) hs.getAttribute("user")).getUiId());
		
		mav.setViewName("rvchat/groupcall");
		return mav;
	}
	
	@RequestMapping(value="/check", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> RandomChatSizeCheck(@RequestBody Map<String,Object> checkMap, HttpSession hs) {
		
		int limitSize = Integer.parseInt((String) checkMap.get("rRSize"));		
		String rRName = "";
		List<Map<String,Object>> watingPeopleList = rrim.selectWatingPeopleList(checkMap);	
		int waitTime = (watingPeopleList.size()%limitSize + 1) * 1500;
		
		if(watingPeopleList.size()%limitSize == 0){			
			rRName = UUID.randomUUID().toString();			
		}else {			
			rRName = (String) watingPeopleList.get((watingPeopleList.size()-1)).get("rRName");
		}		
		checkMap.put("rRName", rRName);
		checkMap.put("uiNickName", ((UserInfoVO) hs.getAttribute("user")).getUiNickName());		
		int result = rrim.insertRandomRoomInfo(checkMap);
		
		int idx = 0;
		boolean timeout = true;
		while(timeout) {
			idx++;
			List<Map<String,Object>> checkSize = rrim.selectRandomRoomInfo(checkMap);
			if(checkSize.size() == limitSize) {
				
				checkMap.put("msg", "매칭되었습니다. 잠시만 기다려주세요.");
				checkMap.put("biz", true);	
				checkMap.put("waitTime", waitTime);
				return checkMap;
			}			
			if(idx==10) {
				timeout = false;	
				checkMap.put("msg", "인원이 부족합니다.");
				checkMap.put("biz", false);
				rrim.deleteRandomRoomInfo(checkMap);
			}			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		return checkMap;
	}
	
	@RequestMapping(value="/get", method=RequestMethod.POST)
	public @ResponseBody List<String> getShareuiNickNameList(@RequestBody Map<String,Object> rMap) {
		List<String> uiIdList = rrim.selectuiNickNameList(rMap);	
		return uiIdList;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteRandomRoomInfo(@RequestBody Map<String,Object> rMap) {
		
		int result = rrim.deleteRandomRoomInfo(rMap);
		rMap.put("biz", false);
		if(result==1) {
			rMap.put("biz", true);			
		}		
		
		return rMap;
	}
	

}
