package com.oneday.sofa.domain.article.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneday.sofa.domain.article.dto.ArticleRequest;
import com.oneday.sofa.domain.article.service.ArticleService;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.global.config.CheckJWT;

@RestController
@RequestMapping("/article")
@CheckJWT
public class ArticleController {
	
	private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	ArticleService articleService;
	
	//글 생성
	@PostMapping
	public void createArticle(JWTMember jwtMember, @Valid ArticleRequest articleRequest) {
		log.info(articleRequest.getTitle() + " " + articleRequest.getContent() + " " + articleRequest.getFiles().size());
		
		articleService.saveArticle(jwtMember, articleRequest);
	}
	
	//TODO:: 글 삭제
	//TODO:: 글 수정
}
