package com.andy.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.andy.memo.post.domain.Post;
import com.andy.memo.post.repository.PostRepository;

import common.FileManager;
import jakarta.persistence.PersistenceException;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public boolean addPost(
			long userId
			, String title
			, String contents
			, MultipartFile file) {
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
		.userId(userId)
		.title(title)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		// imagePath notnull이라서 공백이라도 써야하네요
		// JPA exception 검증 후 수행
		
		try {
			postRepository.save(post);
		} catch (PersistenceException e) {
			return false;
		}
		return true;

	}

	// 로그인 후 list html에 작성 전체 행을 다 조회하는 JPA 근데 특정사용자만 로그인 한 프라이머리 키 인사람만 해야하니까
	public List<Post> getPostList(long userId) {
		
		List<Post> postlist = postRepository.findByUserIdOrderByIdDesc(userId);
		
		return postlist;
		
	}
	
	public Post getPost(long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			
			return optionalPost.get();
			
		} else {
			
			return null;
			
		}
	}
}
