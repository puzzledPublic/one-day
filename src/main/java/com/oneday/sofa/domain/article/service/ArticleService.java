package com.oneday.sofa.domain.article.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneday.sofa.domain.article.dao.ArticleRepository;
import com.oneday.sofa.domain.article.dao.BoardRepository;
import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.article.domain.Board;
import com.oneday.sofa.domain.article.dto.ArticleRequest;
import com.oneday.sofa.domain.article.dto.ArticleResponse;
import com.oneday.sofa.domain.comment.dto.CommentResponse;
import com.oneday.sofa.domain.comment.service.CommentService;
import com.oneday.sofa.domain.member.domain.Member;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.domain.member.service.MemberService;
import com.oneday.sofa.global.error.exception.EntityNotFoundException;
import com.oneday.sofa.global.error.exception.UnAuthorizationException;

@Service
@Transactional
public class ArticleService {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	CommentService commentService;
	
	//글 조회
	public ArticleResponse searchArticle(long articleId) {
		Article article = this.findNotRemovedArticle(articleId);
		List<CommentResponse> comments = commentService.getArticleComments(articleId);
		article.increaseHits();
		return new ArticleResponse(article, comments);
	}
	
	//글 저장
	public void saveArticle(JWTMember jwtMember, ArticleRequest articleRequest) {
		
		Member member = memberService.findMemberById(jwtMember.getId());
		Board board = boardRepository.findById(articleRequest.getBoardId()).orElseThrow(EntityNotFoundException::new);

		String title = articleRequest.getTitle();
		String content = articleRequest.getContent();
		
		Article article = new Article(title, content, member, board);
		
		articleRepository.save(article);
	}
	
	//글 삭제
	public void removeArticle(JWTMember jwtMember, long articleId) {
		Article article = this.findNotRemovedArticle(articleId);
		
		Member owner = article.getMember();
		if(owner.getId() != jwtMember.getId()) {
			throw new UnAuthorizationException();
		}
		
		article.remove();
	}
	
	//글 수정
	public void modifyArticle(JWTMember jwtMember, ArticleRequest articleRequest, long articleId) {
		Article article = this.findNotRemovedArticle(articleId);
		
		Member owner = article.getMember();
		if(owner.getId() != jwtMember.getId()) {
			throw new UnAuthorizationException();
		}
		
		article.updateBy(articleRequest);
	}
	
	public Article findNotRemovedArticle(long articleId) {
		Article article = articleRepository.findByIdAndRemovedFalse(articleId).orElseThrow(() -> new EntityNotFoundException("Article"));
		return article;
	}
	
}
