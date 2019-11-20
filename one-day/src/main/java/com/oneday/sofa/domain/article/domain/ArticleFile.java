package com.oneday.sofa.domain.article.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ArticleFile {
	
	@Id
	@GeneratedValue
	Long id;
	
	@Column(nullable=false, unique=true)
	String name;
	
	String originName;
	
	@ManyToOne
	Article article;
	
	protected ArticleFile() {}
	
	public ArticleFile(String originName, Article article) {
		this.name = UUID.randomUUID().toString();
		this.originName = originName;
		this.article = article;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOriginName() {
		return originName;
	}
	
	public Article getArticle() {
		return article;
	}
}
