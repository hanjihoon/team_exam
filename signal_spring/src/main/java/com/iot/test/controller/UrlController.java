package com.iot.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iot.test.mapper.CategoryMapper;
import com.iot.test.mapper.ColorInfoMapper;
import com.iot.test.mapper.FriendsMapper;
import com.iot.test.mapper.RegeonMapper;
import com.iot.test.service.RoomInfoService;
import com.iot.test.service.UserInRoomService;
import com.iot.test.vo.UserInfoVO;

@Controller
public class UrlController {
	private static final Logger log = LoggerFactory.getLogger(UrlController.class);

	@Autowired
	private CategoryMapper ctm;

	@Autowired
	private ColorInfoMapper clm;

	@Autowired
	private RegeonMapper rgm;

	@Autowired
	private RoomInfoService ris;
	
	@Autowired
	private UserInRoomService uirs;	

	@Autowired
	private FriendsMapper fm;
	
	@RequestMapping("/")
	public String index() {

		return "index";
	}			

	@RequestMapping("/home")
	public String home() {

		return "index";
	}

	@RequestMapping("/header")
	public String header() {

		return "common/header";
	}

	@RequestMapping(value = "/login")
	public String login() {

		return "user/login";
	}

	@RequestMapping("/signup")
	public String signup() {

		return "user/signup";
	}
	
	@RequestMapping("/google/signup")
	public ModelAndView signupGoogle(@RequestParam("id") String id, @RequestParam("email") String email, ModelAndView mav) {
		mav.setViewName("user/signup");
		mav.addObject("id", id);
		mav.addObject("email", email);
		return mav;
	}

	@RequestMapping("/video")
	public ModelAndView groupcall(@RequestParam Map<String, Object> rMap, HttpSession hs) throws JsonProcessingException {
		ModelAndView mav = new ModelAndView();
		String regeonName = (String) rMap.get("regeonName");
		rMap.remove("regeonName");
		rMap.put("regeonNo", ((rgm.selectRegeonNo(regeonName)).get("regeonNo")));
		rMap.put("iconName", ((UserInfoVO) hs.getAttribute("user")).getIconName());		
		ris.insertRoomInfo(rMap);		
		rMap.put("uiId", ((UserInfoVO) hs.getAttribute("user")).getUiId());
		rMap.put("uiNickName", ((UserInfoVO) hs.getAttribute("user")).getUiNickName());		
		uirs.insertUserInRoom(rMap);
		mav.setViewName("vchat/groupcall");
		mav.addObject("rName", rMap.get("rName"));
		mav.addObject("uiId", rMap.get("uiId"));
		mav.addObject("uiNickName", rMap.get("uiNickName"));		
		return mav;
	}
	
	@RequestMapping("/join")
	public ModelAndView joinRoom(@RequestParam Map<String, Object> rMap, HttpSession hs) throws JsonProcessingException {
		ModelAndView mav = new ModelAndView();	
		rMap.put("uiId", ((UserInfoVO) hs.getAttribute("user")).getUiId()); 
		rMap.put("uiNickName", ((UserInfoVO) hs.getAttribute("user")).getUiNickName());			
		uirs.insertUserInRoom(rMap);
		mav.setViewName("vchat/groupcall");
		mav.addObject("rName", rMap.get("rName"));
		mav.addObject("uiId", rMap.get("uiId"));
		mav.addObject("uiNickName", rMap.get("uiNickName"));
		
		return mav;
	}
	

	@RequestMapping("/map")
	public ModelAndView map(ModelAndView mav) {

		mav.addObject("ctList", ctm.selectCategoryList());
		mav.addObject("clList", clm.selectColorList());
		mav.addObject("rgList", rgm.selectRegeonList());

		mav.setViewName("map/map");
		return mav;
	}
	
	@RequestMapping("/myfriends")
	public ModelAndView goMyFriends(HttpSession hs) {
		int idx = 0;
		ModelAndView mav = new ModelAndView();
		String uiId = ((UserInfoVO) hs.getAttribute("user")).getUiId();	
		List<Map<String,Object>> friendsList = fm.selectFriendsListByUiId(uiId);
		
		List<Map<String,Object>> friendsTargetList = fm.selectFriendsListByUiIdAsFId(uiId);		
		List<Map<String,Object>> acceptList = new ArrayList<Map<String,Object>>();
		
		for(int i=0; i<friendsTargetList.size(); i++) {
			String myId = (String) friendsTargetList.get(i).get("fId");
			String otherId = (String) friendsTargetList.get(i).get("uiId");
			Map<String,Object> fMap = new HashMap<String,Object>();
			fMap.put("uiId", myId);
			fMap.put("fId", otherId);	
			if(fm.selectFriendsListCheck(fMap).size()!=1) {
				friendsTargetList.get(i).put("count",idx);
				acceptList.add(friendsTargetList.get(i));
				idx++;
			}
		}
		mav.addObject("callList", acceptList);		
		mav.addObject("fList", friendsList);					
		mav.setViewName("mypage/myfriends");
		return mav;		
	}


	
	
	
	@RequestMapping("/myprofile")
	public String goMyProfile() {
		
		return "mypage/myprofile";
		
	}

}
