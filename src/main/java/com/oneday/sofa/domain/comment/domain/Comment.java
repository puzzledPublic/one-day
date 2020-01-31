package com.oneday.sofa.domain.comment.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.oneday.sofa.domain.comment.dto.CommentRequest;
import com.oneday.sofa.domain.common.EntityDate;
import com.oneday.sofa.domain.common.RecommendOrNot;
import com.oneday.sofa.domain.member.domain.Member;

@Entity
public class Comment {
	
	@Transient
	public static final long PARENT_COMMENT = 0L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long parentId;
	
	@Lob
	private String content;
	
	private Long articleId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Member member;
	
	@Embedded
	private RecommendOrNot recommendOrNot;
	
	@Embedded
	private EntityDate dates;
	
	private boolean removed;
	
	protected Comment() {}
	
	private Comment(Long articleId, String content, Member member) {
		this.articleId = articleId;
		this.content = content;
		this.member = member;
		this.recommendOrNot = new RecommendOrNot();
		this.dates = new EntityDate();
		this.removed = false;
	}
	
	public Comment(Long articleId, String content, Member member, long parentId) {
		this(articleId, content, member);
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public String getContent() {
		if(isRemoved()) return "삭제 된 댓글입니다.";
		return content;
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public Member getMember() {
		return member;
	}

	public RecommendOrNot getRecommendOrNot() {
		return recommendOrNot;
	}

	public EntityDate getDates() {
		return dates;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	
	public boolean isNested() {
		return this.getParentId() != Comment.PARENT_COMMENT;
	}
	
	public void updateBy(CommentRequest commentRequest) {
		this.content = commentRequest.getContent();
		this.getDates().updateDate();
	}
}
