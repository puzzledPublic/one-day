package com.oneday.sofa.domain.recommend.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Recommend {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long memberId;
	
	private boolean liked;
	
	protected Recommend() {}
	
	public Recommend(Long memberId, boolean liked) {
		this.memberId = memberId;
		this.liked = liked;
	}
	
	public Long getMemberId() {
		return memberId;
	}
	
	public boolean isLiked() {
		return liked;
	}
	
}
