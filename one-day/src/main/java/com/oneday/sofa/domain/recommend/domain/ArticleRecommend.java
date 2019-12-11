package com.oneday.sofa.domain.recommend.domain;

import javax.persistence.Entity;

@Entity
public class ArticleRecommend extends Recommend {

	private Long articleId;
	
	protected ArticleRecommend() {}
	
	public ArticleRecommend(Long articleId, Long memberId, boolean liked) {
		super(memberId, liked);
		this.articleId = articleId;
	}
	
	public Long getArticleId() {
		return articleId;
	}
}
