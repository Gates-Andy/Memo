package com.andy.memo.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.andy.memo.post.domain.Post;
import com.andy.memo.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public boolean addPost(long userId, String title, String contents) {
		Post post = Post.builder().userId(userId).title(title).contents(contents).imagePath("").build();
		// imagePath notnull이라서 공백이라도 써야하네요
		// JPA exception 검증 후 수행
		try {
			postRepository.save(post);
		} catch (PersistenceException e) {
			return false;
		}
		return true;

	}

	// 로그인 후 list html에 작성
	public List<Post> getPostList() {
		return postRepository.findAll();
	}
}
