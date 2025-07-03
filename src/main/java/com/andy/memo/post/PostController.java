package com.andy.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andy.memo.post.domain.Post;
import com.andy.memo.post.dto.PostDto;
import com.andy.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/create-view")
	public String inputPost() {
		return "post/input";
	}
	
	@GetMapping("/list-view")
	public String postList(
			HttpSession session
			, Model model) {
		
		Object userIdObj = session.getAttribute("userId");
		
		if (userIdObj == null) {
			// 로그인 안된 사용자 처리: 예를 들어 로그인 페이지로 리다이렉트
			return "redirect:/user/login-view";
		}
		
		long userId = (long) session.getAttribute("userId");
		
		List<PostDto> postlist = postService.getPostList();
		
		model.addAttribute("postlist", postlist);
		
		return "post/list";
	}

	@GetMapping("/detail-view")
	public String postDetail(
			@RequestParam("id") long id
			, Model model) {
		
		Post memo = postService.getPost(id);
		
		model.addAttribute("memo", memo);
		
		return "post/detail";

	}
}
