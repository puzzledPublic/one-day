package com.oneday.sofa.domain.article.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.oneday.sofa.domain.board.domain.Board;
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
	
	@ManyToOne
	private Member member;
	
	@ManyToOne
	private Board board;
	
	private int hits;
	
	@Embedded
	private RecommendOrNot recommendOrNot;

	@Embedded
	private EntityDate dates;
	
	protected Article() {}

	public Article(String title, String content, Member member, Board board, int hits, RecommendOrNot recommendOrNot) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.board = board;
		this.hits = hits;
		this.recommendOrNot = recommendOrNot;
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
	
}
