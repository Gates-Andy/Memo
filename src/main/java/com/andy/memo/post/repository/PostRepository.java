package com.andy.memo.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andy.memo.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	 
		List<Post> findAll();
}
