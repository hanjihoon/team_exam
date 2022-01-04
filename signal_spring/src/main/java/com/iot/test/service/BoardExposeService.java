package com.iot.test.service;

import java.util.List;

import com.iot.test.vo.BoardExposeVO;

public interface BoardExposeService {
	public List<Integer> exposeUiIdList(int bNo);

	public int insertExpose(BoardExposeVO bev);
}
