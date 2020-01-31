package com.oneday.sofa.domain.comment.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CommentRequest {
	
	@Min(1)
	private long articleId;
	
	@Min(0)
	private long parentId;
	
	@NotEmpty
	private String content;
	
	protected CommentRequest() {}
	
	public CommentRequest(long articleId, long parentId, String content) {
		this.articleId = articleId;
		this.parentId = parentId;
		this.content = content;
	}
	
	public long getArticleId() {
		return articleId;
	}
	
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	
	public long getParentId() {
		return parentId;
	}
	
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
