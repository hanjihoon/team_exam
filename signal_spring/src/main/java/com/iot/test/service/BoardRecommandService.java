package com.iot.test.service;

import java.util.List;

import com.iot.test.vo.BoardRecommandVO;

public interface BoardRecommandService {
	
	public List<Integer> recommandUiIdList(int bNo);
	
	public int insertRecommand(BoardRecommandVO brv);
}
