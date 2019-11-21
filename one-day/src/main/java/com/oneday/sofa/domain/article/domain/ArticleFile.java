package com.oneday.sofa.domain.article.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class ArticleFile {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	private String originName;
	
	@ManyToOne
	private Article article;
	
	protected ArticleFile() {}
	
	public ArticleFile(String name, String originName) {
		this.name = name;
		this.originName = originName;
	}
	
	public ArticleFile(String name, String originName, Article article) {
		this(name, originName);
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
	
	public void setArticle(Article article) {
		this.article = article;
	}
	
	//TODO:: 저장 코드 분리
	static public List<ArticleFile> toArticleList(List<MultipartFile> multipartFiles) {
		if(multipartFiles.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<ArticleFile> articleFiles = new ArrayList<>();
		for(MultipartFile multiPartFile: multipartFiles) {
			String originName = multiPartFile.getOriginalFilename();
			String originExtension = originName.substring(originName.lastIndexOf("."));
			String newName = UUID.randomUUID().toString() + originExtension;
			
			try {
				File file = new File("C:\\Users\\KHM\\Pictures\\test\\" + newName);
				multiPartFile.transferTo(file);
			}
			catch(IOException | IllegalStateException ex) {
				throw new RuntimeException("upload file save fail!!!", ex);
			}
			ArticleFile articleFile = new ArticleFile(newName, originName);
			
			articleFiles.add(articleFile);
		}
		
		return articleFiles;
	}
}
