package com.iot.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.test.mapper.BoardRecommandMapper;
import com.iot.test.service.BoardRecommandService;
import com.iot.test.vo.BoardRecommandVO;

@Service
public class BoardRecommandServiceImpl implements BoardRecommandService {

	@Autowired
	BoardRecommandMapper brm;

	@Override
	public List<Integer> recommandUiIdList(int bNo) {
		return brm.recomUiIdList(bNo);
	}

	@Override
	public int insertRecommand(BoardRecommandVO brv) {
		int uiNo = brv.getUiNo();
		List<Integer> recommandUiNoList = brm.recomUiIdList(brv.getbNo());
		for (int rUiNo : recommandUiNoList) {
			if (uiNo == rUiNo) {
				return 0;
			}
		}
		return brm.insertRecom(brv);
	}


}
