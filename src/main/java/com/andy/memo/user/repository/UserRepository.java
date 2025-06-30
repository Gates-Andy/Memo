package com.andy.memo.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.memo.user.domain.User;

@Mapper
public interface UserRepository {
	// 1. 회원가입
	public int insertUser(@Param("loginId") String loginId, @Param("email") String email,
			@Param("password") String password, @Param("name") String name);

	// 2. 중복확인
	public int selectCountByloginId(@Param("loginId") String loginId);

	// 3. 로그인
	public User selectUser(@Param("loginId") String loginId, @Param("password") String password);

}
