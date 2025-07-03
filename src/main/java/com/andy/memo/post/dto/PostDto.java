package com.andy.memo.post.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PostDto {
	private long id;
	
	private String title;
	private String contents;
	private String imagePath;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	private long userId;
	private String loginId;
}	
