package com.oneday.sofa.domain.article.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.oneday.sofa.domain.article.dto.ArticleRequest;
import com.oneday.sofa.domain.common.EntityDate;
import com.oneday.sofa.domain.common.RecommendOrNot;
import com.oneday.sofa.domain.member.domain.Member;

@Entity
public class Article {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	@Lob
	private String content;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Member member;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Board board;
	
	private int hits;
	
	@Embedded
	private RecommendOrNot recommendOrNot;

	@Embedded
	private EntityDate dates;
	
	private boolean removed;	//삭제시 DB서 지우는것이 아닌 삭제 체크만 해준다.
	
	protected Article() {}

	public Article(String title, String content, Member member, Board board) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.board = board;
		this.hits = 0;
		this.recommendOrNot = new RecommendOrNot();
		this.dates = new EntityDate();
		this.removed = false;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public Member getMember() {
		return member;
	}

	public Board getBoard() {
		return board;
	}
	
	public int getHits() {
		return hits;
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
	
	public void setRemoved() {
		this.removed = true;
	}
	
	public void updateBy(ArticleRequest articleRequest) {
		this.title = articleRequest.getTitle();
		this.content = articleRequest.getContent();
		this.getDates().updateDate();
	}
	
	public void increaseHits() {
		this.hits++;
	}
}
