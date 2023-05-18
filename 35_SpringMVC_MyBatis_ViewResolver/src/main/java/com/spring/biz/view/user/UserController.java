package com.spring.biz.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.user.UserService;
import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

@RequestMapping("/user") //모든 요청명에 공통으로 적용
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	public UserController() {
		System.out.println("==========> UserController() 객체 생성");
	}
	
	// /login.do 요청이면서 POST 방식 요청인 경우에만 처리
	//@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@PostMapping("/login.do") // 4.3 버전부터 사용가능 + <mvc:annotation-driven />
	public String login(UserVO vo) {
		System.out.println(">>> 로그인 처리");
		System.out.println("vo : " + vo);

		UserVO user = userService.getUser(vo);
		System.out.println("user : " + user);
		
		if (user != null) {
			System.out.println(">> 로그인 성공!!!");
			return "getBoardList.do";
		} else {
			System.out.println(">> 로그인 실패!!!");
			return "login.jsp";
		}
	}	
	
	/* @ModelAttribute : 모델(Model)의 속성값으로 저장(속성명 별도 지정)
		별도 명칭 부여 안하면 <데이터타입>의 첫글자 소문자로 작성된 명칭 사용
		@ModelAttribute UserVO vo ---> userVO 명칭 사용
		@ModelAttribute("user") UserVO vo ---> 속성명 user 사용
	*/
	//@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	@GetMapping("/login.do")
	public String loginView(@ModelAttribute("user") UserVO vo) {
		System.out.println(">>> 로그인 화면으로 이동 - loginView()");
		System.out.println("vo : " + vo);
		//vo.setId("test");
		//vo.setPassword("test");
		System.out.println("설정후 vo : " + vo);
		
		return "user/login";
	}
	
	@RequestMapping("/logout.do")
	public String handleRequest(HttpSession session) {
		System.out.println(">>> 로그아웃 처리");
		session.invalidate();
		return "login.jsp";
	}
	

}
