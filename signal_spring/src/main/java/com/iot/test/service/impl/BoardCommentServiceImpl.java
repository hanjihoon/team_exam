package com.iot.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.test.mapper.BoardCommentMapper;
import com.iot.test.service.BoardCommentService;
import com.iot.test.vo.BoardCommentVO;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {

	@Autowired
	BoardCommentMapper dcm;

	@Override
	public List<BoardCommentVO> boardComentList() {

		return dcm.boardCommentList();
	}

	@Override
	public List<BoardCommentVO> selectComentByBNo(Integer bNo) {

		return dcm.selectCommentByBNo(bNo);
	}

	@Override
	public int insertComent(BoardCommentVO bcv) {

		return dcm.insertComment(bcv);
	}

	@Override
	public int deleteComent(Integer bcNo) {

		return dcm.deleteComment(bcNo);
	}

}
