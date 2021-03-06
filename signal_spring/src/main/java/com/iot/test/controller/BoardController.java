package com.iot.test.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iot.test.common.page.Paging;
import com.iot.test.service.impl.BoardCommentServiceImpl;
import com.iot.test.service.impl.BoardHitServiceImpl;
import com.iot.test.service.impl.BoardRecommandServiceImpl;
import com.iot.test.service.impl.BoardServiceImpl;
import com.iot.test.service.impl.ImageServiceImpl;
import com.iot.test.service.impl.UserInfoServiceImpl;
import com.iot.test.vo.BoardCommentVO;
import com.iot.test.vo.BoardRecommandVO;
import com.iot.test.vo.BoardVO;
import com.iot.test.vo.ImageVO;
import com.iot.test.vo.UserInfoVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	ImageServiceImpl imageService;

	@Autowired
	BoardServiceImpl boardService;

	@Autowired
	UserInfoServiceImpl userService;

	@Autowired
	BoardCommentServiceImpl boardComentService;

	@Autowired
	BoardHitServiceImpl boardHitService;

	@Autowired
	BoardRecommandServiceImpl boardRecommandService;

	ModelAndView goBoard(ModelAndView mav, int page, int block) {
		int maxContent = boardService.selectBoardCount();
		Paging p = new Paging(maxContent);
		p.setCurrentPage(page);
		p.setCurrentBlock(block);
		List<BoardVO> boardList = boardService.boardList((page - 1) * 20);
		mav.addObject("boardList", boardList);
		mav.addObject("page", p);
		mav.setViewName("board/board");
		return mav;
	}

	ModelAndView goBoard(ModelAndView mav) {
		int page = 1;
		int block = 1;
		int maxContent = boardService.selectBoardCount();
		Paging p = new Paging(maxContent);
		p.setCurrentPage(page);
		p.setCurrentBlock(block);
		List<BoardVO> boardList = boardService.boardList((page - 1) * 20);
		mav.addObject("boardList", boardList);
		mav.addObject("page", p);
		mav.setViewName("board/board");
		return mav;
	}

	@RequestMapping
	public ModelAndView boardListPage(@RequestParam("page") int page, @RequestParam("block") int block,
			ModelAndView mav) {
		return goBoard(mav, page, block);
	}

	@RequestMapping("/write")
	public String writeBoard() {
		return "board/write";
	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public ModelAndView uploadImage(@RequestParam("filedata") List<MultipartFile> images, BoardVO bv, HttpSession hs,
			ModelAndView mav) {

		UserInfoVO uiv = (UserInfoVO) hs.getAttribute("user");
		String uiNickName = uiv.getUiNickName();

		bv.setUiNickName(uiNickName);
		// ????????? ?????? ??????
		boardService.insertBoard(bv);
		int bNo = boardService.selectBoardCurrSeq();
		log.info("/complete bNo={}", bNo);
		// ????????? ?????? ??????
		imageService.insertImg(images, bNo);

		return goBoard(mav);
	}

	// ????????? ?????????????????? ??????
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateBoard(int bNo, ModelAndView mav) {
		List<ImageVO> imageVOList = imageService.selectByBno(bNo);
		mav.addObject("imageVOList", imageVOList);
		mav.setViewName("board/update");
		return mav;
	}

	// ????????? ?????? ??????
	@RequestMapping(value = "/update/complete", method = RequestMethod.POST)
	public ModelAndView updateComplete(@RequestParam("filedata") List<MultipartFile> images,
			@RequestParam("imgNoList") List<Integer> imgNoList, BoardVO bv, HttpSession hs, ModelAndView mav) {
		int bNo = bv.getbNo();
		// ????????? ????????? ????????? ?????????
		List<ImageVO> imageVOList = imageService.selectByBno(bNo);
		// ????????? ???????????? ???????????? ?????? ?????? ????????? ????????? ????????? ?????? (??????????????? ????????? ?????????)
		if (imgNoList.size() != 0 && imageVOList.size() != 0) {
			imageService.updateImg(imageVOList, imgNoList);
		} else {
			imageService.deleteImg(bNo);
		}
		// ????????? ???????????? ??????, ??????
		imageService.insertImg(images, bNo);
		boardService.updateBoard(bv);
		return goBoard(mav);
	}

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public @ResponseBody void writeComent(@RequestBody BoardCommentVO bcv, HttpSession hs, ModelAndView mav) {
		log.info("BoardCommentVO={}", bcv);
		boardComentService.insertComent(bcv);
		boardService.updateBoardCCP(bcv.getbNo());
	}

	@RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
	public @ResponseBody void deleteComent(@RequestBody BoardCommentVO bcv) {
		int bcNo = bcv.getBcNo();
		boardComentService.deleteComent(bcNo);
		boardService.updateBoardCCM(bcv.getbNo());
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void deleteBoard(@RequestBody BoardVO bv, ModelAndView mav) {
		int bNo = bv.getbNo();
		List<ImageVO> imageList = imageService.selectByBno(bNo);
		// ????????? ???????????? ??? ?????? ??????
		for (ImageVO image : imageList) {
			String imgId = image.getImgId();
			File imgF = new File(imageService.IMAGE_DIR, imgId);
			imgF.delete();
		}
		// BD ????????? ??????, ????????? ?????? ??????(cascade ?????? ???????????? ????????? ????????? ???????????? ?????? ????????? ????????? ???????????? ?????? ?????????)
		boardService.deleteBoard(bNo);
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ModelAndView boardPage(@RequestParam int bNo, String hSessionId, ModelAndView mav, HttpSession hs) {
		// ????????? ??????
		if (boardHitService.insertHit(bNo, hSessionId) == 1) {
			boardService.updateBoardHit(bNo);
		}
		// ????????? ??????
		BoardVO boardVO = boardService.selectByNo(bNo);
		// ????????? ????????? ??????
		List<ImageVO> imageVOList = imageService.selectByBno(bNo);
		// ????????? ?????? ??????
		UserInfoVO uiv = (UserInfoVO) hs.getAttribute("user");
		// ?????? ??????
		List<BoardCommentVO> comentList = boardComentService.selectComentByBNo(bNo);
		mav.addObject("boardVO", boardVO);
		mav.addObject("imageVOList", imageVOList);
		mav.addObject("comentList", comentList);
		if (uiv != null) {
			mav.addObject("loginUserInfoVO", uiv);
		}
		mav.setViewName("board/post");
		return mav;
	}

	@RequestMapping(value = "/mypost", method = RequestMethod.GET)
	public ModelAndView boardMyPage(@RequestParam("page") int page, @RequestParam("block") int block, ModelAndView mav,
			HttpSession hs) {
		UserInfoVO ui = (UserInfoVO) hs.getAttribute("user");
		String uiNickName = ui.getUiNickName();
		int myPostCount = boardService.selectByNickCount(uiNickName);
		if (myPostCount == 0) {
			block = 0;
		}
		Paging p = new Paging(myPostCount);
		p.setCurrentPage(page);
		p.setCurrentBlock(block);
		List<BoardVO> boardList = boardService.selectByNick(uiNickName, (page - 1) * 20);
		mav.addObject("boardList", boardList);
		mav.addObject("page", p);
		mav.addObject("myPost", true);
		mav.setViewName("board/board");
		return mav;
	}

	@RequestMapping("/recommand")
	public @ResponseBody void recommandBoard(@RequestBody BoardRecommandVO brv) {
		if (boardRecommandService.insertRecommand(brv) == 1) {
			boardService.updateBoardRecommand(brv.getbNo());
		}
	}

	@RequestMapping("/expose")
	public @ResponseBody void exposeBoard(@RequestBody BoardRecommandVO brv) {
		if (boardRecommandService.insertRecommand(brv) == 1) {
			boardService.updateBoardRecommand(brv.getbNo());
		}
	}
}
