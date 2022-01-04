package com.iot.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.test.mapper.BoardMapper;
import com.iot.test.service.BoardService;
import com.iot.test.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper bm;

	@Override
	public int selectBoardCount() {

		return bm.selectBoardCount();
	}

	@Override
	public int selectBoardCurrSeq() {

		return bm.selectBoardCurrSeq();
	}

	@Override
	public List<BoardVO> boardList(int page) {

		return bm.boardList(page);
	}

	@Override
	public List<BoardVO> searchBoardList(BoardVO bv, int page) {

		return bm.searchBoardList(bv, page);
	}
	
	@Override
	public List<BoardVO> selectByNick(String uiNickName, int page) {
		
		return bm.selectByNick(uiNickName,page);
	}

	@Override
	public BoardVO selectByNo(Integer bNo) {

		return bm.selectByNo(bNo);
	}

	@Override
	public int insertBoard(BoardVO bv) {

		return bm.insertBoard(bv);
	}

	@Override
	public int deleteBoard(Integer bNo) {

		return bm.deleteBoard(bNo);
	}

	@Override
	public int updateBoard(BoardVO bv) {

		return bm.updateBoard(bv);
	}

	@Override
	public int updateBoardHit(int bNo) {

		return bm.updateBoardHit(bNo);
	}

	@Override
	public int updateBoardRecommand(int bNo) {

		return bm.updateBoardRecom(bNo);
	}

	@Override
	public int updateBoardCCP(int bNo) {

		return bm.updateBoardCCP(bNo);
	}

	@Override
	public int updateBoardCCM(int bNo) {

		return bm.updateBoardCCM(bNo);
	}

	@Override
	public int selectByNickCount(String uiNickName) {
		
		return bm.selectByNickCount(uiNickName);
	}

}
