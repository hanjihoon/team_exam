package com.iot.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.test.mapper.IconInfoMapper;

@Controller
@RequestMapping("/icon")
public class IconController {
	
	@Autowired
	IconInfoMapper iim;
	
	@RequestMapping(value="/get", method=RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> getIconList(){
		
		List<Map<String,Object>> iconList = iim.selectIconInfoList();
		
		return iconList;
	}

}
