package com.oneday.sofa.domain.article.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneday.sofa.domain.article.dao.ArticleRepository;
import com.oneday.sofa.domain.article.dao.BoardRepository;
import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.article.domain.Board;
import com.oneday.sofa.domain.article.dto.ArticleRequest;
import com.oneday.sofa.domain.article.dto.ArticleResponse;
import com.oneday.sofa.domain.member.dao.MemberRepository;
import com.oneday.sofa.domain.member.domain.Member;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.domain.member.exception.MemberNotFoundException;
import com.oneday.sofa.global.error.exception.EntityNotFoundException;
import com.oneday.sofa.global.error.exception.UnAuthorizationException;

@Service
@Transactional
public class ArticleService {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	BoardRepository boardRepository;
	
	//글 조회
	public ArticleResponse searchArticle(long articleId) {
		Article article = this.findNotRemovedArticle(articleId);
		article.increaseHits();
		return new ArticleResponse(article);
	}
	
	//글 저장
	public void saveArticle(JWTMember jwtMember, ArticleRequest articleRequest) {
		
		Member member = memberRepository.findById(jwtMember.getId()).orElseThrow(MemberNotFoundException::new);
		Board board = boardRepository.findById(articleRequest.getBoardId()).orElseThrow(EntityNotFoundException::new);

		String title = articleRequest.getTitle();
		String content = articleRequest.getContent();
//		List<MultipartFile> uploadFiles = articleRequest.getFiles();
//		List<ArticleFile> articleFiles = ArticleFile.toArticleFileList(uploadFiles);
		
		Article article = new Article(title, content, member, board);
//		article.addArticleFiles(articleFiles);
		
		articleRepository.save(article);
	}
	
	//글 삭제
	public void removeArticle(JWTMember jwtMember, long articleId) {
		Article article = this.findNotRemovedArticle(articleId);
		
		Member owner = article.getMember();
		if(owner.getId() != jwtMember.getId()) {
			throw new UnAuthorizationException();
		}
		
		article.setRemoved();
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
	
	private Article findNotRemovedArticle(long articleId) {
		Article article = articleRepository.findByIdAndRemovedFalse(articleId).orElseThrow(EntityNotFoundException::new);
		return article;
	}
	
}
