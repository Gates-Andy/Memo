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

	@PostMapping("/join")
	public Map<String, String> join(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("username") String username) {

		Map<String, String> resultMap = new HashMap<>();
		if (userService.addUser(email, password, name, username)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		return resultMap;

	}

	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(@RequestParam String username) {

		Map<String, Boolean> resultMap = new HashMap<>();
		if (userService.isDuplicateId(username)) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}

		return resultMap;

	}
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("username") String username
			, @RequestParam("password") String password, HttpServletRequest request){
		
		Map<String, String> resultMap = new HashMap<>();
		
		User user = userService.getUser(username, password);
		
		if(user != null) {
			resultMap.put("result", "success");
			
			// 세션을 관리하는 객체
			// 요청한 대상 클라이언트의 세션
			HttpSession session =request.getSession();
			
			// 로그인이 되었다.
			// 사용자 정보를 저장
			// 세션은 모든 요청에서 접근하고 사용할 수 있다.
			// 사용자를 구분할 수 있는 가밧을 저장해보자. username key에 값이 저장되어 있으면 로그인된 ㄴ상태다
			session.setAttribute("userId", user.getId()); //pri
			session.setAttribute("userName", user.getName());
			
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
