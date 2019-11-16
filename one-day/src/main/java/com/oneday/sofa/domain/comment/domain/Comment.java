package com.oneday.sofa.domain.comment.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.common.EntityDate;
import com.oneday.sofa.domain.common.RecommendOrNot;
import com.oneday.sofa.domain.member.domain.Member;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Lob
	private String content;
	
	@ManyToOne
	private Article article;
	
	@ManyToOne
	private Member member;
	
	@Embedded
	private RecommendOrNot recommendOrNot;
	
	@Embedded
	private EntityDate dates;
	
	protected Comment() {}
	
	public Comment(String content, Article article, Member member, RecommendOrNot recommendOrNot) {
		this.content = content;
		this.article = article;
		this.member = member;
		this.recommendOrNot = recommendOrNot;
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Article getArticle() {
		return article;
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
	
}
