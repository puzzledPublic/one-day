package com.oneday.sofa.domain.comment.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private List<CommentResponse> childComments = new ArrayList<>();

	protected CommentResponse() {
	}

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

	public List<CommentResponse> getChildComments() {
		return childComments;
	}

	public void setChildComments(List<CommentResponse> childComments) {
		this.childComments = childComments;
	}

	//Comment들을 중첩 관계를 이루도록(parent 참조) 생성
	//TODO::보기 좋게 로직 짜기
	public static List<CommentResponse> toList(List<Comment> comments) {
		comments.sort((c1, c2) -> {
			if (c1.getParentId() == c2.getParentId()) {
				return c1.getDates().getCreatedAt().compareTo(c2.getDates().getCreatedAt());
			}
			return Long.compare(c1.getParentId(), c2.getParentId());
		});

		Map<Long, CommentResponse> parentCommentMap = new HashMap<>();
		Map<Long, List<CommentResponse>> childCommentMap = new HashMap<>();

		for (Comment comment : comments) {
			if (comment.getParentId() == 0L) {
				parentCommentMap.put(comment.getId(), new CommentResponse(comment));
				childCommentMap.put(comment.getId(), new ArrayList<CommentResponse>());
			} else {
				childCommentMap.get(comment.getParentId()).add(new CommentResponse(comment));
			}
		}

		List<CommentResponse> commentResponses = new ArrayList<>();
		for (Long key : childCommentMap.keySet()) {
			CommentResponse parentComment = parentCommentMap.get(key);
			parentComment.setChildComments(childCommentMap.getOrDefault(key, new ArrayList<>()));
			commentResponses.add(parentComment);
		}

		return commentResponses;
	}
}
