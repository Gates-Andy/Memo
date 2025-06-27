package com.andy.memo.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.memo.user.domain.User;

@Mapper
public interface UserRepository {
	public int insertUser(
			@Param("email") String email
			, @Param("password") String password
			, @Param("name") String name
			,@Param("username") String username);
	
	public int selectCountByUsername(@Param("username") String username);
	//login
	public User selectUser(@Param("username") String username, @Param("password") String password);
	
}


