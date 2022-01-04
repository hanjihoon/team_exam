package com.iot.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iot.test.mapper.BoardExposeMapper;
import com.iot.test.service.BoardExposeService;
import com.iot.test.vo.BoardExposeVO;

public class BoardExposeSeviceImpl implements BoardExposeService {
	@Autowired
	BoardExposeMapper bem;

	@Override
	public List<Integer> exposeUiIdList(int bNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertExpose(BoardExposeVO bev) {
		int uiNo = bev.getUiNo();
		List<Integer> recommandUiNoList = bem.exposeUiIdList(bev.getbNo());
		for (int rUiNo : recommandUiNoList) {
			if (uiNo == rUiNo) {
				return 0;
			}
		}
		return bem.insertExpose(bev);
	}

}
