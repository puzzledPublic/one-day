package com.oneday.sofa.domain.article.dto;

import java.util.List;

import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.comment.dto.CommentResponse;
import com.oneday.sofa.domain.common.EntityDate;
import com.oneday.sofa.domain.member.dto.MemberResponse;

public class ArticleResponse {
	
	private Long id;
	
	private String title;
	
	private String content;
	
	private MemberResponse member;
	
	private List<CommentResponse> comments;
	
	private int hits;
	
	private EntityDate dates;
	
	protected ArticleResponse() {}
	
	public ArticleResponse(Article article, List<CommentResponse> comments) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.member = new MemberResponse(article.getMember());
		this.comments = comments;
		this.hits = article.getHits();
		this.dates = article.getDates();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public MemberResponse getMember() {
		return member;
	}
	
	public List<CommentResponse> getComments() {
		return comments;
	}
	
	public int getHits() {
		return hits;
	}
	
	public EntityDate getDates() {
		return dates;
	}
}
