package com.andy.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.memo.user.service.UserService;

@RequestMapping("/user")
@RestController // API를 위한 컨트롤러
public class UserRestController {

	private final UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("email") String email
			, @RequestParam("password") String password
			,@RequestParam("name") String name
			, @RequestParam("username") String username) {

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
	
}
