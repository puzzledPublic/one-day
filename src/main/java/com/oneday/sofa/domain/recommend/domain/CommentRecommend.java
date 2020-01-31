package com.oneday.sofa.domain.recommend.domain;

import javax.persistence.Entity;

@Entity
public class CommentRecommend extends Recommend {

	private Long commentId;
	
	protected CommentRecommend() {}
	
	public CommentRecommend(Long commentId, Long memberId, boolean liked) {
		super(memberId, liked);
		this.commentId = commentId;
	}
	
	public Long getCommentId() {
		return commentId;
	}
}
