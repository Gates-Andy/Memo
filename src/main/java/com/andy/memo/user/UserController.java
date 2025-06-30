package com.andy.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		// 세션에 저장한 사용자 정보 삭제
		HttpSession session = request.getSession();

		session.removeAttribute("userId");
		session.removeAttribute("userName");
		// 세션 값 제거 후 로그인 화면으로 리다이렉트
		return "redirect:/user/login-view";

	}
}
