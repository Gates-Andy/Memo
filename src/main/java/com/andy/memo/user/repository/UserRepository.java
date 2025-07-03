package com.andy.memo.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.memo.user.domain.User;

@Mapper
public interface UserRepository {
	// 1. 회원가입
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("email") String email
			, @Param("password") String password
			, @Param("name") String name);

	// 2. 중복확인
	public int selectCountByloginId(@Param("loginId") String loginId);

	// 3. 로그인
	public User selectUser(
			@Param("loginId") String loginId
			, @Param("password") String password);

	//4. 마이바티스 기반 프라이머리키로 유저 찾아오기 post 서비스 에서 사용할거라서 여기까지옴
	public User selectUserById(@Param("id") long id);
}
