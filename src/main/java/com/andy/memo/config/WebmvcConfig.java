package com.andy.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.andy.memo.common.FileManager;

@Configuration // 이 클래스가 스프링의 Java 기반 설정 클래스임을 나타낸다.
public class WebmvcConfig implements WebMvcConfigurer { // WebMvcConfig 클래스는 WebMvcConfigurer 인터페이스를 구현하는 클래스이다. 이 클래스에는
														// 웹 애플리케이션의 MVC 구성을 설정한다.

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { // 이 메서드는 정적 리소스에 대한 핸들러를 등록하는 역할을 한다.
		registry.addResourceHandler("/images/**") // url에 /images 로 시작하는 경우 uploadPath 에 설정한 폴더기준으로 파일 읽어오도록 설정
													// URL 패턴이 "/images/**" 로 시작하는 경우, 해당 요청을 정적 리소스로 처리하도록 핸들러를 추가
				.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
		// 실제파일이 저장된 경로
		// 로컬 컴퓨터에 저장된 파일을 읽어올 root 경로 설정
		// 이 핸들러에게 정적 리소스를 어디에서 찾을지 설정한다.
		// 'uploadPath' 에 설정된 폴더를 기준으로 파일을 찾아올 것이다.
	}

}
