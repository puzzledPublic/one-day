package com.oneday.sofa.domain.article.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneday.sofa.domain.article.dto.ArticleRequest;
import com.oneday.sofa.domain.article.dto.ArticleResponse;
import com.oneday.sofa.domain.article.service.ArticleService;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.global.config.CheckJWT;

@RestController
@RequestMapping("/article")
public class ArticleController {
	
	private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	ArticleService articleService;
	
	//글 조회
	@GetMapping("/{articleId}")
	public ArticleResponse getArticle(@PathVariable long articleId) {
		return articleService.searchArticle(articleId);
	}
	
	//글 생성
	@CheckJWT
	@PostMapping
	public void createArticle(JWTMember jwtMember, @Valid ArticleRequest articleRequest) {
		log.info(articleRequest.getTitle() + " " + articleRequest.getContent() + " " /* + articleRequest.getFiles().size()*/);
		
		articleService.saveArticle(jwtMember, articleRequest);
	}
	
	//글 삭제
	@CheckJWT
	@DeleteMapping("/{articleId}")
	public void deleteArticle(JWTMember jwtMember, @PathVariable long articleId) {
		articleService.removeArticle(jwtMember, articleId);
	}
	
	//글 수정
	@CheckJWT
	@PutMapping("/{articleId}")
	public void updateArticle(JWTMember jwtMember, @Valid ArticleRequest articleRequest, @PathVariable long articleId) {
		articleService.modifyArticle(jwtMember, articleRequest, articleId);
	}
}
