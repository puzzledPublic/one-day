package com.oneday.sofa.domain.article.dto;

import java.util.List;

import com.oneday.sofa.domain.article.domain.Board;

public class BoardResponse {
	
	private String boardName;
	
	private String displayName;

	private List<ArticleInfo> articleInfoList;
	
	private PageInfo pageInfo;
	
	
	public BoardResponse(Board board, List<ArticleInfo> articleInfoList, PageInfo pageInfo) {
		this.boardName = board.getName();
		this.displayName = board.getDisplayName();
		this.articleInfoList = articleInfoList;
		this.pageInfo = pageInfo;
	}
	
	public String getBoardName() {
		return boardName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public List<ArticleInfo> getArticleInfoList() {
		return articleInfoList;
	}
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
}
