package com.oneday.sofa.domain.article.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneday.sofa.domain.article.dto.ArticleRequest;

@RestController
@RequestMapping("/article")
public class ArticleController {
	
	private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

	//글 생성
	@PostMapping
	public void createArticle(@Valid ArticleRequest articleRequest) {
		log.info(articleRequest.getTitle() + " " + articleRequest.getContent() + " " + articleRequest.getFiles().size());
	}
	
	//글 삭제
	//글 수정
}
