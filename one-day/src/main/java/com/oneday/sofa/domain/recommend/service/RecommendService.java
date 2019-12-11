package com.oneday.sofa.domain.recommend.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.article.service.ArticleService;
import com.oneday.sofa.domain.comment.domain.Comment;
import com.oneday.sofa.domain.comment.service.CommentService;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.domain.recommend.dao.ArticleRecommendRepository;
import com.oneday.sofa.domain.recommend.dao.CommentRecommendRepository;
import com.oneday.sofa.domain.recommend.domain.ArticleRecommend;
import com.oneday.sofa.domain.recommend.domain.CommentRecommend;
import com.oneday.sofa.domain.recommend.dto.ArticleRecommendRequest;
import com.oneday.sofa.domain.recommend.dto.CommentRecommendRequest;
import com.oneday.sofa.domain.recommend.exception.AlreadyRecommendedException;

@Service
public class RecommendService {

	@Autowired
	ArticleService articleService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	ArticleRecommendRepository articleRecommendRepository;
	
	@Autowired
	CommentRecommendRepository commentRecommendRepository;
	
	@Transactional
	public void recommendArticle(JWTMember jwtMember, ArticleRecommendRequest recommendRequest) {
		Long memberId = jwtMember.getId();
		Long articleId = recommendRequest.getId();
		Article article = articleService.findNotRemovedArticle(articleId);
		
		Optional<ArticleRecommend> recommendOp = articleRecommendRepository.findByArticleIdAndMemberId(articleId, memberId);
		if(recommendOp.isPresent()) {
			
			ArticleRecommend articleRecommend = recommendOp.get();
			if(articleRecommend.isLiked() == recommendRequest.isLiked()) {
				article.getRecommendOrNot().decrease(recommendRequest.isLiked());
				articleRecommendRepository.delete(articleRecommend);
			}else {
				throw new AlreadyRecommendedException();
			}
			
		}else {
			article.getRecommendOrNot().increase(recommendRequest.isLiked());
			articleRecommendRepository.save(new ArticleRecommend(articleId, memberId, recommendRequest.isLiked()));
		}
	}
	
	@Transactional
	public void recommendComment(JWTMember jwtMember, CommentRecommendRequest recommendRequest) {
		Long memberId = jwtMember.getId();
		Long commentId = recommendRequest.getId();
		Comment comment = commentService.findComment(commentId);
		
		Optional<CommentRecommend> recommendOp = commentRecommendRepository.findByCommentIdAndMemberId(commentId, memberId);
		if(recommendOp.isPresent()) {
			
			CommentRecommend commentRecommend = recommendOp.get();
			if(commentRecommend.isLiked() == recommendRequest.isLiked()) {
				comment.getRecommendOrNot().decrease(recommendRequest.isLiked());
				commentRecommendRepository.delete(commentRecommend);
			}else {
				throw new AlreadyRecommendedException();
			}
			
		}else {
			comment.getRecommendOrNot().increase(recommendRequest.isLiked());
			commentRecommendRepository.save(new CommentRecommend(commentId, memberId, recommendRequest.isLiked()));
		}
	}
}
