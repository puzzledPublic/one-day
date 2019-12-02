package com.oneday.sofa.domain.article.dto;

import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.common.EntityDate;
import com.oneday.sofa.domain.member.domain.Member;

public class BoardResponse {

	private Long id;
	
	private String title;
	
	private String userName;
	
	private int replyCount;
	
	private int hits;
	
	private EntityDate dates;
	
	public BoardResponse(Article article, int replyCount) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.userName = article.getMember().getUserName();
		this.replyCount = replyCount;
		this.hits = article.getHits();
		this.dates = article.getDates();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getReplyCount() {
		return replyCount;
	}
	
	public int getHits() {
		return hits;
	}
	
	public EntityDate getDates() {
		return dates;
	}
}
