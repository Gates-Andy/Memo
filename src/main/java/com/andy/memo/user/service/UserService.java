package com.andy.memo.user.service;

import org.springframework.stereotype.Service;

import com.andy.memo.user.reposiitory.UserRepository;

@Service
public class UserService {

	// final : 해당 변수에 값이ㅣ 저장된 후 수정 불가
	private final UserRepository userRepository;

	// 해당 클래스가 생성될때 Spring 이 생성자를 호출해서 객체를 주입해준다.
	// 다른 ㄴ생성자 없이 Autowired를 위한 생성자만 있는 경우 Autowired 어노테이션 생략 가능!!
	// @Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// 사용자 추가 기능
	public boolean addUser(String logIn, String password, String name, String email){
		int count = userRepository.insertUser(logIn, password, name, email);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
