package com.andy.memo.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.andy.memo.post.domain.Post;
import com.andy.memo.post.dto.PostDto;
import com.andy.memo.post.repository.PostRepository;
import com.andy.memo.user.domain.User;
import com.andy.memo.user.service.UserService;

import common.FileManager;
import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	// @autowired 생략 지금 2개 나 주입 받은거임
	private final PostRepository postRepository;
	private final UserService userService;

	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	public boolean addPost(long userId, String title, String contents, MultipartFile file) {

		String imagePath = FileManager.saveFile(userId, file);

		Post post = Post.builder().userId(userId).title(title).contents(contents).imagePath(imagePath).build();

		// imagePath notnull이라서 공백이라도 써야하네요
		// JPA exception 검증 후 수행

		try {
			postRepository.save(post);
		} catch (PersistenceException e) {
			return false;
		}
		return true;

	}

	// 로그인 후 list html에 작성 전체 행을 다 조회하는 JPA 근데 특정사용자만 로그인 한 프라이머리 키 인사람만 해야하니까 +
	// 무슨기능인지..
	public List<PostDto> getPostList() {

		List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

		List<PostDto> postDtoList = new ArrayList<>();

		for (Post post : postList) {

			User user = userService.getUserById(post.getUserId());

			PostDto postDto = PostDto.builder()
					.id(post.getId())
					.title(post.getTitle())
					.contents(post.getContents())
					.imagePath(post.getImagePath())
					.createdAt(post.getCreatedAt())
					.loginId(user.getLoginId())
					.build();

			postDtoList.add(postDto);
		}

		return postDtoList;

	}

	public Post getPost(long id) {
		Optional<Post> optionalPost = postRepository.findById(id);

		if (optionalPost.isPresent()) {

			return optionalPost.get();

		} else {

			return null;

		}
	}
}
