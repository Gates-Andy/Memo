package com.andy.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.memo.user.domain.User;
import com.andy.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController // API를 위한 컨트롤러
public class UserRestController {
	private final UserService userService;
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	// 1. login
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		Map<String, String> resultMap = new HashMap<>();
		if (userService.addUser(loginId, password, name, email)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}

	// 2. 중복확인
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(@RequestParam String loginId) {
		Map<String, Boolean> resultMap = new HashMap<>();
		if (userService.isDuplicateId(loginId)) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}
		return resultMap;
	}

	@PostMapping("/login")
	public Map<String, String> login(@RequestParam("loginId") String loginId, @RequestParam("password") String password,
			HttpServletRequest request) { // HttpServletRequest request: 세션을 꺼내기 위해 필요합니다.
		Map<String, String> resultMap = new HashMap<>();
		User user = userService.getUser(loginId, password);
		if (user != null) {
			resultMap.put("result", "success");
			// 세션을 관리하는 객체 클라이언트(사용자)의 세션 객체를 얻어옵니다.
			// 세션은 로그인 상태, 사용자 정보 등을 서버가 기억할 수 있게 해줍니다.요청한 대상 클라이언트의 세션
			HttpSession session = request.getSession();
			// 로그인이 되었다.
			// 사용자 정보를 저장
			// 세션은 모든 요청에서 접근하고 사용할 수 있다.
			// 사용자를 구분할 수 있는 가밧을 저장해보자. loginId key에 값이 저장되어 있으면 로그인된 ㄴ상태다
			session.setAttribute("userId", user.getId()); // pri DB의 user id
			session.setAttribute("loginId", user.getName());
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	
}
