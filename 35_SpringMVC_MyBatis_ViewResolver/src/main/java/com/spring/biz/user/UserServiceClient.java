package com.spring.biz.user;

import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {

	public static void main(String[] args) {
		System.out.println("---- 스프링 컨테이너 구동전 ---------");
		//1. 스프링 컨테이너 구동(스프링 설정파일 읽어서)
		GenericXmlApplicationContext container
				= new GenericXmlApplicationContext("applicationContext.xml");
		
		System.out.println("---- 스프링 컨테이너 구동후 ---------");
		//2. 스프링 컨테이너 사용
		UserService userService = (UserService) container.getBean("userService");
		
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test");
		
		UserVO user = userService.getUser(vo);
		System.out.println("user : " + user);
		
		System.out.println("---- 스프링 컨테이너 사용후 종료 처리 ---------");
		//3. 스프링 컨테이너 종료
		container.close();

	}

}

