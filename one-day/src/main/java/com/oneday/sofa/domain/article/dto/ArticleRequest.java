package com.oneday.sofa.domain.article.dto;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.oneday.sofa.domain.common.validation.UploadFile;


public class ArticleRequest {
	
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	@Min(1)
	private Long boardId;

	private List<@UploadFile MultipartFile> files = Collections.emptyList();
	
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
	
	public Long getBoardId() {
		return boardId;
	}
	
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	
	public List<MultipartFile> getFiles() {
		return files;
	}
	
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
}
