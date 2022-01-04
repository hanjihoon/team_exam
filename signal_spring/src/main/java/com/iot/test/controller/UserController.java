package com.iot.test.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.test.service.UserInfoService;
import com.iot.test.vo.UserInfoVO;


@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserInfoService uis;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> login(@RequestBody UserInfoVO ui, HttpSession hs) {
		log.info("UserInfoVO=>{}",ui);
		Map<String,Object> rMap = new HashMap<String,Object>();
		uis.setUserInfoList(rMap, ui, hs);		
        return rMap;
    }
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(HttpSession hs) {
		hs.removeAttribute("user");
        return "/user/login";
    }

	@RequestMapping("/signup")
    public @ResponseBody Map<String,Object> signup(@RequestBody UserInfoVO uiv) {
		log.info("UserInfoVO=>{}", uiv);
		Map<String,Object> resultMap = uis.insertUserInfo(uiv);
		
        return resultMap;
    }
	
	@RequestMapping("/check")
    public @ResponseBody Map<String,Object> checkId(@RequestBody UserInfoVO uiv) {
		log.info("UserInfoVO=>{}", uiv);
		
		Map<String,Object> checkMap = uis.checkUiId(uiv);
		
        return checkMap;
    }
	@RequestMapping(value="/uiId", method=RequestMethod.POST)
    public @ResponseBody String getUiId(HttpSession hs) {
		
		UserInfoVO uiVO = (UserInfoVO) hs.getAttribute("user");
		String name = uiVO.getUiNickName();
		log.info("uiId=>{}",name);
        return name;
    }
}
