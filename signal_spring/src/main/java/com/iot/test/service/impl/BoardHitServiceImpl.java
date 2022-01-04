package com.iot.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.test.mapper.BoardHitMapper;
import com.iot.test.service.BoardHitService;
import com.iot.test.vo.BoardHitVO;

@Service
public class BoardHitServiceImpl implements BoardHitService {

	@Autowired
	BoardHitMapper bhm;

	@Override
	public List<String> hitSessionIdList(int bNo) {
		
		return bhm.hitSessionIdList(bNo);
	}
	@Override
	public int insertHit(int bNo, String hSessionId) {
		List<String> hitrSessionIdList = bhm.hitSessionIdList(bNo);
		boolean isSessionId = false;
		if (hitrSessionIdList != null) {
			for (String sessionId : hitrSessionIdList) {
				if (hSessionId.equals(sessionId)) {
					isSessionId = true;
				}
			}
		}
		if (!isSessionId) {
			BoardHitVO nBhv = new BoardHitVO();
			nBhv.setbNo(bNo);
			nBhv.sethSessionId(hSessionId);
			return bhm.insertHit(nBhv);
		}
		return 0;
	}

}
