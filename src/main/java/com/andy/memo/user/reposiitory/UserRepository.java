package com.andy.memo.user.reposiitory;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
	public int insertUser(@Param("logIn") String logIn, @Param("password") String password, @Param("name") String name,
			@Param("email") String email);
}
