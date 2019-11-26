package com.oneday.sofa.domain.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FileInfo {
	
	@Column(nullable=false, unique=true)
	private String name;
	
	private String originName;
	
	protected FileInfo() {}
	
	public FileInfo(String name, String originName) {
		this.name = name;
		this.originName = originName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOriginName() {
		return originName;
	}
}
