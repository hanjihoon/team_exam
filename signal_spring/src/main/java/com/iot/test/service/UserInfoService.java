package com.iot.test.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.iot.test.vo.UserInfoVO;

public interface UserInfoService {
	
	void setUserInfoList(Map<String,Object> rMap, UserInfoVO ui, HttpSession hs);
	Map<String,Object> insertUserInfo(UserInfoVO uiv);
	Map<String,Object> checkUiId(UserInfoVO uiv);

}
