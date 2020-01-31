package com.oneday.sofa.domain.recommend.dto;

import javax.validation.constraints.Min;

public class CommentRecommendRequest extends RecommendRequest {
	
	@Min(1)
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
