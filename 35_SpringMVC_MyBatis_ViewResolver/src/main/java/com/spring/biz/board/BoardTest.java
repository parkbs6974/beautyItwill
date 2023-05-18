package com.spring.biz.board;

import java.util.List;

import com.spring.biz.board.impl.BoardDAO;
import com.spring.biz.board.impl.BoardDAO;

public class BoardTest {

	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		
		List<BoardVO> list = dao.getBoardList();
		for (BoardVO board : list) {
			System.out.println(board);
		}
		System.out.println("---------------");
		
		System.out.println("=== Insert =====");
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트");
		vo.setWriter("홍길동");
		vo.setContent("테스트-내용");
		
		dao.insertBoard(vo);
		
		System.out.println("=== Update =====");
		
		BoardVO updateVO = new BoardVO();
		updateVO.setSeq(2);
		updateVO.setTitle("제목-수정");
		updateVO.setContent("내용-수정");
		
		dao.updateBoard(updateVO);
		
		System.out.println("=== Delete =====");
		BoardVO deleteVO = new BoardVO();
		deleteVO.setSeq(5);
		
		dao.deleteBoard(deleteVO);
		
		System.out.println("=== getBoard : 1개 조회 =====");
		BoardVO selectVO = new BoardVO();
		selectVO.setSeq(2);
		
		BoardVO selectedBoard = dao.getBoard(selectVO);
		
		System.out.println("selectedBoard : " + selectedBoard);
		
		//전체 데이터 확인 -------------------
		System.out.println("---------------");
		list = dao.getBoardList();
		for (BoardVO board : list) {
			System.out.println(board);
		}

	}

}
