package com.oneday.sofa.domain.board.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.oneday.sofa.domain.article.domain.Article;

@Entity
public class Board {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="board")
	private List<Article> articles = new ArrayList<>();
	
	protected Board() {}
	
	public Board(String name, List<Article> articles) {
		this.name = name;
		this.articles = articles;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}


	public List<Article> getArticles() {
		return articles;
	}
}
