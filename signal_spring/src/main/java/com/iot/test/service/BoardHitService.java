package com.iot.test.service;

import java.util.List;

import com.iot.test.vo.BoardHitVO;

public interface BoardHitService {
	public List<String> hitSessionIdList(int bNo);
	
	public int insertHit(int bNo, String hSessionId);
}
