package com.andy.memo.post.service;

import org.springframework.stereotype.Service;

import com.andy.memo.post.domain.Post;
import com.andy.memo.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {

	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public boolean addPost(long userId, String title, String contents) {

		Post post = Post.builder().userId(userId).title(title).contents(contents).build();

		// JPA exception 검증 후 수행
		try {

			postRepository.save(post);

		} catch (PersistenceException e) {
			
			return false;
			
		}
		
		return true;

	}
}
