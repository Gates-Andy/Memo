package com.andy.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller // VIEW를 위한 컨트롤러
public class UserController {
	
	@GetMapping("/join-view")
	public String joinInput() {
		
		return "user/join";
		
	}
	
	@GetMapping("/login-view")
	public String loginInput() {
		
		return "user/login";
		
	}
	
}
