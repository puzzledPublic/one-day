package com.oneday.sofa.domain.article.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


public class ArticleRequest {
	
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	@NotEmpty
	private String boardName;

//	private List<@UploadFile MultipartFile> files = Collections.emptyList();
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getBoardName() {
		return boardName;
	}
	
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
//	public List<MultipartFile> getFiles() {
//		return files;
//	}
//	
//	public void setFiles(List<MultipartFile> files) {
//		this.files = files;
//	}
	
}
