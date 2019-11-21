package com.oneday.sofa.domain.article.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@OneToMany(mappedBy="article", cascade=CascadeType.ALL)
	private List<ArticleFile> articleFiles = new ArrayList<>(); 
	
	private int hits;
	
	@Embedded
	private RecommendOrNot recommendOrNot;

	@Embedded
	private EntityDate dates;
	
	protected Article() {}

	public Article(String title, String content, Member member, Board board) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.board = board;
		this.hits = 0;
		this.recommendOrNot = new RecommendOrNot(0, 0);
		this.dates = new EntityDate(LocalDateTime.now(), LocalDateTime.now());
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
	
	public List<ArticleFile> getArticleFiles() {
		return articleFiles;
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
	
	public void addArticleFile(ArticleFile articleFile) {
		if(this == articleFile.getArticle()) {
			return;
		}
		articleFile.setArticle(this);
		this.getArticleFiles().add(articleFile);
	}
	
	public void addArticleFiles(List<ArticleFile> articleFiles) {
		for(ArticleFile articleFile : articleFiles) {
			this.addArticleFile(articleFile);
		}
	}
}
