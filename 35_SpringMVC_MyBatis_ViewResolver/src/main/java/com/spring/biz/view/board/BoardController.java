package com.spring.biz.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;

/*
@SessionAttributes : 같은 컨트롤러에서 모델(Model)객체 공유해서 사용하려는 경우 사용
	단, 현재(동일) 컨트롤러에서만 사용가능
	사용후에는 SessionStatus 객체의 setComplete() 메소드로 사용해제
*/
@RequestMapping("/board") //모든 요청명에 공통으로 적용
@SessionAttributes("board") // board 라는 이름의 Model 객체가 있으면 session 에 저장
@Controller
public class BoardController {
	//@Autowired
	private BoardService boardService;
	
	public BoardController() {
		System.out.println("==========> BoardController() 객체 생성");
	}
	
	@Autowired
	public BoardController(BoardService boardService) {
		System.out.println("==========> BoardController(boardService) 객체 생성");
		this.boardService = boardService;
	}
	
	
	// 메소드에 선언된 @ModelAttribute 는 리턴된 데이터를 View에 전달
	// @ModelAttribute 선언된 메소드는 @RequestMapping 메소드보다 먼저 실행된다
	// 뷰에 전달될 때 설정된 명칭(예: conditionMap)
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("=====> Map searchConditionMap() 실행");
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	// 리턴타입 : ModelAndView ---> String
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 상세보기");
		System.out.println("vo : " + vo);
		
		BoardVO board = boardService.getBoard(vo);
		model.addAttribute("board", board); // Model 객체 사용 View에 데이터 전달
		System.out.println("DB데이터 board : " + board);
		
		return "getBoard.jsp";
	}	
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 전체 목록 보여주기");
		System.out.println("vo : " + vo);

		//List<BoardVO> list = boardDAO.getBoardList();
		List<BoardVO> list = boardService.getBoardList(vo);
		model.addAttribute("boardList", list);
		
		return "getBoardList.jsp";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException {
		System.out.println(">>> 게시글 입력처리");
		System.out.println("vo : " + vo);
		
		MultipartFile uploadFile = vo.getUploadFile();
		System.out.println("> uploadFile : " + uploadFile);
		
		/* 파일업로드 관련 
		MultipartFile 인터페이스 주요메소드
		String getOriginalFilename() : 업로드 할 원본파일명 찾기
		void transferTo(File dest) : 업로드 할 파일을 업로드(복사) 처리
		boolean isEmpty() : 업로드할 파일 존재 여부(없으면 true)
		*/
		if (uploadFile == null) {
			System.out.println("::: uploadFile 파라미터가 전달되지 않았을 때");
		} else if (uploadFile.isEmpty()) {
			System.out.println("::: 전달받은 파일 데이터가 없을 경우");
		} else { //파일 데이터가 있을 때
			System.out.println("uploadFile.isEmpty() : " + uploadFile.isEmpty());
			String fileName = uploadFile.getOriginalFilename(); //원본 파일명
			System.out.println("::: 원본파일명 : " + fileName);
			String savedFileName = UUID.randomUUID().toString();
			System.out.println("::: 저장파일명 : " + savedFileName);
			
			//물리적 파일 복사
			//uploadFile.transferTo(new File("c:/MyStudy/temp/" + savedFileName));
			
			String destPathFile = "c:/MyStudy/temp/" + savedFileName;
			uploadFile.transferTo(new File(destPathFile));
		}
		
		boardService.insertBoard(vo);
		
		return "getBoardList.do";
	}

	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println(">>> 게시글 수정처리");
		System.out.println("vo : " + vo);
		
		boardService.updateBoard(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, SessionStatus sessionStatus) {
		System.out.println(">>> 게시글 삭제처리");
		System.out.println("vo : " + vo);
		
		boardService.deleteBoard(vo);
		sessionStatus.setComplete(); //session 데이터 삭제
		
		return "getBoardList.do";
	}
	
	
}
