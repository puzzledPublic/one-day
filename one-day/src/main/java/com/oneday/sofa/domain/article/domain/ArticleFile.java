package com.oneday.sofa.domain.article.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import com.oneday.sofa.domain.common.FileInfo;
import com.oneday.sofa.global.util.FileHelper;

//wysiwyg으로 인한 보류.
//@Entity
public class ArticleFile {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private FileInfo fileInfo;
	
	@ManyToOne
	private Article article;
	
	protected ArticleFile() {}
	
	public ArticleFile(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public ArticleFile(FileInfo fileInfo, Article article) {
		this(fileInfo);
		this.article = article;
	}
	
	public Long getId() {
		return id;
	}
	
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	
	public Article getArticle() {
		return article;
	}
	
	public void setArticle(Article article) {
		this.article = article;
	}
	
	static public List<ArticleFile> toArticleFileList(List<MultipartFile> multipartFiles) {
		if(multipartFiles.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<ArticleFile> articleFiles = new ArrayList<>();
		List<FileInfo> fileInfos = FileHelper.saveMultipartFiles(multipartFiles);
		
		for(FileInfo fileInfo : fileInfos) {
			articleFiles.add(new ArticleFile(fileInfo));
		}
		
		return articleFiles;
	}
}
