package com.iot.test.service;

import java.util.List;

import com.iot.test.vo.BoardCommentVO;

public interface BoardCommentService {
	
	public List<BoardCommentVO> boardComentList();

	public List<BoardCommentVO> selectComentByBNo(Integer bNo);

	public int insertComent(BoardCommentVO bcv);

	public int deleteComent(Integer bNo);

}
