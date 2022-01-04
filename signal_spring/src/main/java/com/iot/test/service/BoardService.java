package com.iot.test.service;

import java.util.List;

import com.iot.test.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardList(int page);

	public List<BoardVO> searchBoardList(BoardVO bv, int page);

	public int selectBoardCount();

	public int selectBoardCurrSeq();
	
	public int selectByNickCount(String uiNickName);

	public BoardVO selectByNo(Integer bNo);

	public List<BoardVO> selectByNick(String uiNickName, int page);

	public int insertBoard(BoardVO bv);

	public int deleteBoard(Integer bcNo);

	public int updateBoard(BoardVO bv);

	public int updateBoardHit(int bNo);

	public int updateBoardRecommand(int bNo);

	public int updateBoardCCP(int bNo);

	public int updateBoardCCM(int bNo);
}
