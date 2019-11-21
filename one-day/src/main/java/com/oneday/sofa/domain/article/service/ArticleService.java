package com.oneday.sofa.domain.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oneday.sofa.domain.article.dao.ArticleRepository;
import com.oneday.sofa.domain.article.dao.BoardRepository;
import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.article.domain.ArticleFile;
import com.oneday.sofa.domain.article.domain.Board;
import com.oneday.sofa.domain.article.dto.ArticleRequest;
import com.oneday.sofa.domain.member.dao.MemberRepository;
import com.oneday.sofa.domain.member.domain.Member;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.domain.member.exception.MemberNotFoundException;
import com.oneday.sofa.global.error.exception.EntityNotFoundException;

@Service
public class ArticleService {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	BoardRepository boardRepository;
	
	public void saveArticle(JWTMember jwtMember, ArticleRequest articleRequest) {
		String title = articleRequest.getTitle();
		String content = articleRequest.getContent();
		List<MultipartFile> uploadFiles = articleRequest.getFiles();
		List<ArticleFile> articleFiles = ArticleFile.toArticleList(uploadFiles);
		Member member = memberRepository.findById(jwtMember.getId()).orElseThrow(MemberNotFoundException::new);
		Board board = boardRepository.findById(articleRequest.getBoardId()).orElseThrow(EntityNotFoundException::new);
		
		Article article = new Article(title, content, member, board);
		article.addArticleFiles(articleFiles);
		
		articleRepository.save(article);
	}
	
}
