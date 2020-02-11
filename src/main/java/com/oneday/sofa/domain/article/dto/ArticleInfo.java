package com.oneday.sofa.domain.article.dto;

import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.common.EntityDate;

public class ArticleInfo {

	private Long id;
	
	private String title;
	
	private String userName;
	
	private String nickName;
	
	private long replyCount;
	
	private int hits;
	
	private EntityDate dates;
	
	public ArticleInfo(Article article, long replyCount) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.userName = article.getMember().getUserName();
		this.nickName = article.getMember().getNickName();
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
	
	public String getNickName() {
		return nickName;
	}
	
	public long getReplyCount() {
		return replyCount;
	}
	
	public int getHits() {
		return hits;
	}
	
	public EntityDate getDates() {
		return dates;
	}
}
