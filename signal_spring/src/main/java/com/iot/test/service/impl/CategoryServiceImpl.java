package com.iot.test.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.iot.test.mapper.CategoryMapper;
import com.iot.test.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {	

	
	@Autowired
	private CategoryMapper ctm;
	
	@Override
	public void setCategoryList(Map<String, Object> cMap) {
		
		cMap.put("list", ctm.selectCategoryList());
		
	}

}
