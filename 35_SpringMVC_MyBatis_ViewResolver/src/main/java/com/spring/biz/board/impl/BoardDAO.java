package com.spring.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	public BoardDAO() {
		System.out.println(">> BoardDAOMybatis() 객체 생성");
	}
	
	//글입력
	public void insertBoard(BoardVO vo) {
		System.out.println("===> MyBatis 사용 insertBoard() 실행");
		mybatis.insert("boardDAO.insertBoard", vo);
	}

	//글수정(실습)
	public void updateBoard(BoardVO vo) {
		System.out.println("===> MyBatis 사용 updateBoard() 실행");
		mybatis.update("boardDAO.updateBoard", vo);
	}

	//글삭제(실습)
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> MyBatis 사용 deleteBoard() 실행");
		mybatis.delete("boardDAO.deleteBoard", vo);
	}

	//글 1개 조회(실습)
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> MyBatis 사용 getBoard() 실행");
		return mybatis.selectOne("boardDAO.getBoard", vo);
	}

	//글 목록 조회(모든 게시글)
	public List<BoardVO> getBoardList() {
		System.out.println("===> MyBatis 사용 getBoardList() 실행");
		
		return null;
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> MyBatis 사용 getBoardList(vo) 실행");
		List<BoardVO> list = null;
		
		// 검색조건 값이 없을 때 기본값 설정
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		
		if ("TITLE".equals(vo.getSearchCondition())) {
			list = mybatis.selectList("boardDAO.getBoardList_T", vo.getSearchKeyword());
		} else {
			list = mybatis.selectList("boardDAO.getBoardList_C", vo.getSearchKeyword());
		}
		
		return list;
	}
	
}

