package com.oneday.sofa.domain.article.dto;

import java.util.List;

public class BoardResponse {
	
	private List<ArticleInfo> articleInfoList;
	
	private PageInfo pageInfo;
	
	public BoardResponse(List<ArticleInfo> articleInfoList, PageInfo pageInfo) {
		this.articleInfoList = articleInfoList;
		this.pageInfo = pageInfo;
	}
	
	public List<ArticleInfo> getArticleInfoList() {
		return articleInfoList;
	}
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
}
