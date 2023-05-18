package com.spring.biz.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.user.UserVO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public UserDAO() {
		System.out.println("=== UserDAOMybatis() 객체 생성");
	}
	
	public UserVO getUser(UserVO vo) {
		System.out.println("===> MyBatis 사용 getUser() 실행");
		return mybatis.selectOne("userDAO.getUser", vo);
	}

}
