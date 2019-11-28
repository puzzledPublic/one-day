package com.oneday.sofa.domain.article.dto;

import java.util.ArrayList;
import java.util.List;

import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.article.domain.ArticleFile;
import com.oneday.sofa.domain.comment.domain.Comment;
import com.oneday.sofa.domain.comment.dto.CommentResponse;
import com.oneday.sofa.domain.common.EntityDate;
import com.oneday.sofa.domain.member.dto.MemberResponse;

public class ArticleResponse {
	
	private Long id;
	
	private String title;
	
	private String content;
	
	private List<String> fileNames;
	
	private MemberResponse member;
	
	private List<CommentResponse> comments;
	
	private EntityDate dates;
	
	protected ArticleResponse() {}
	
	public ArticleResponse(Article article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.fileNames = setFileNames(article.getArticleFiles());
		this.member = new MemberResponse(article.getMember());
		this.comments = setComments(article.getComments());
		this.dates = article.getDates();
	}
	
	private List<String> setFileNames(List<ArticleFile> articleFiles) {
		List<String> fileNames = new ArrayList<>();
		articleFiles.forEach((articleFile) -> fileNames.add(articleFile.getFileInfo().getName()));
		return fileNames;
	}
	
	private List<CommentResponse> setComments(List<Comment> comments) {
		List<CommentResponse> commentResponses = new ArrayList<>();
		comments.forEach((comment) -> commentResponses.add(new CommentResponse(comment)));
		return commentResponses;
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
	
	public List<String> getFileNames() {
		return fileNames;
	}
	
	public MemberResponse getMember() {
		return member;
	}
	
	public List<CommentResponse> getComments() {
		return comments;
	}
	
	public EntityDate getDates() {
		return dates;
	}
}
