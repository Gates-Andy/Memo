package com.andy.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andy.memo.post.domain.Post;
import com.andy.memo.post.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/list-view")
	public String postList(Model model) {
		List<Post> postlist = postService.getPostList();

		model.addAttribute("memoList", postlist);

		return "post/list";
	}

	@GetMapping("/create-view")
	public String inputPost() {
		return "post/input";
	}

}
