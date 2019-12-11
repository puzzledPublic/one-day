package com.oneday.sofa.domain.recommend.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.domain.recommend.dto.ArticleRecommendRequest;
import com.oneday.sofa.domain.recommend.dto.CommentRecommendRequest;
import com.oneday.sofa.domain.recommend.service.RecommendService;
import com.oneday.sofa.global.config.CheckJWT;

@RestController
@RequestMapping("/recommend")
public class RecommendController {
	
	private static final Logger log = LoggerFactory.getLogger(RecommendController.class);

	@Autowired
	RecommendService recommendService;
	
	@CheckJWT
	@PostMapping("/article")
	public void articleLiked(JWTMember jwtMember, @RequestBody @Valid ArticleRecommendRequest recommendRequest) {
		recommendService.recommendArticle(jwtMember, recommendRequest);
	}

	@PostMapping("/comment")
	public void commentLiked(JWTMember jwtMember, @RequestBody @Valid CommentRecommendRequest recommendRequest) {
		recommendService.recommendComment(jwtMember, recommendRequest);
	}
}
