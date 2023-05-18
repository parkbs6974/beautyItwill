package com.spring.biz.view.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;

//@Controller
@RestController //클래스 내의 요청처리 결과가 모두 @ResponseBody 처리됨
public class BoardAjaxController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getJsonBoardList.do")
	//@ResponseBody // response 응답객체의 몸체(body)에 데이터 전달
	public List<BoardVO> getJsonBoardList(BoardVO vo) {
		System.out.println("====== BoardAjaxController.getAjaxBoardList() 실행");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		System.out.println("boardList : " + boardList);
		
		return boardList;
	}
	
	@RequestMapping(value = "/getJsonBoard.do", method = RequestMethod.POST,
			produces = "application/json;charset=UTF-8")
	//@ResponseBody
	public BoardVO getJsonBoard(@RequestBody BoardVO vo) { //@RequestBody post방식 전달 데이터 처리
		System.out.println("====== BoardAjaxController.getJsonBoard() 실행");
		System.out.println("getJsonBoard() vo : " + vo);
		BoardVO board = boardService.getBoard(vo);
		System.out.println("getJsonBoard() board : " + board);
		
		return board;
	}
	
}










