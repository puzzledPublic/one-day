package com.oneday.sofa.domain.comment.dto;

import com.oneday.sofa.domain.comment.domain.Comment;
import com.oneday.sofa.domain.common.EntityDate;
import com.oneday.sofa.domain.common.RecommendOrNot;
import com.oneday.sofa.domain.member.dto.MemberResponse;

public class CommentResponse {
	
	private Long id;
	
	private String content;
	
	private MemberResponse member;
	
	private RecommendOrNot recommendOrNot;
	
	private EntityDate dates;
	
	protected CommentResponse() {}
	
	public CommentResponse(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.member = new MemberResponse(comment.getMember());
		this.recommendOrNot = comment.getRecommendOrNot();
		this.dates = comment.getDates();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	
	public MemberResponse getMember() {
		return member;
	}
	
	public RecommendOrNot getRecommendOrNot() {
		return recommendOrNot;
	}
	
	public EntityDate getDates() {
		return dates;
	}
}
