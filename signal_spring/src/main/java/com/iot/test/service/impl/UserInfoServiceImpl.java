package com.iot.test.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.test.mapper.FriendsMapper;
import com.iot.test.mapper.UserInfoMapper;
import com.iot.test.service.UserInfoService;
import com.iot.test.vo.UserInfoVO;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper uim;
	
	@Autowired
	private FriendsMapper fm;

	@Override
	public void setUserInfoList(Map<String, Object> rMap, UserInfoVO ui, HttpSession hs) {
		UserInfoVO userVO = null;
		rMap.put("msg", "로그인에 실패하셨습니다.");
		rMap.put("biz", false);
		if (ui.getUiPwd() == null) {
			userVO = uim.selectUserById(ui);
		} else {
			userVO = uim.selectUserForLogin(ui);
		}
		if (userVO != null) {
			rMap.put("msg", "로그인에 성공하셨습니다.");
			rMap.put("biz", true);
			hs.setAttribute("user", userVO);
			
			List<Map<String,Object>> friendsTargetList = fm.selectFriendsListByUiIdAsFId(userVO.getUiId());			
			List<Map<String,Object>> acceptList = new ArrayList<Map<String,Object>>();
			
			for(int i=0; i<friendsTargetList.size(); i++) {
				String myId = (String) friendsTargetList.get(i).get("fId");
				String otherId = (String) friendsTargetList.get(i).get("uiId");
				Map<String,Object> fMap = new HashMap<String,Object>();
				fMap.put("uiId", myId);
				fMap.put("fId", otherId);	
				if(fm.selectFriendsListCheck(fMap).size()!=1) {					
					acceptList.add(friendsTargetList.get(i));					
				}
			}
			hs.setAttribute("acceptSize", acceptList.size());
			
		}
	}

	@Override
	public Map<String, Object> insertUserInfo(UserInfoVO uiv) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("msg", "회원가입에 실패하셨습니다.");
		resultMap.put("biz", false);
		if (uim.insertUserInfo(uiv) != 0) {
			resultMap.put("msg", "회원가입에 성공하셨습니다.");
			resultMap.put("biz", true);
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> checkUiId(UserInfoVO uiv) {

		uiv = uim.selectUiId(uiv);

		Map<String, Object> checkMap = new HashMap<String, Object>();
		checkMap.put("msg", "중복된 아이디가 있습니다.");
		checkMap.put("biz", false);
		if (uiv == null) {
			checkMap.put("msg", "사용가능한 아이디입니다.");
			checkMap.put("biz", true);
		}

		return checkMap;
	}
}
