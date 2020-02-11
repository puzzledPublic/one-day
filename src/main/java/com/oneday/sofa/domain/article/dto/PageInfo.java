package com.oneday.sofa.domain.article.dto;

import org.springframework.data.domain.Page;

public class PageInfo {
	private boolean first;
	
	private boolean last;
	
	private int totalPages;
	
	private long totalElements;

	private int size;
	
	private boolean empty;
	
	@SuppressWarnings("rawtypes")
	public PageInfo(Page page) {
		this.first = page.isFirst();
		this.last = page.isLast();
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		this.size = page.getSize();
		this.empty = page.isEmpty();
	}
	
	public boolean isFirst() {
		return first;
	}
	
	public boolean isLast() {
		return last;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public long getTotalElements() {
		return totalElements;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return empty;
	}
}
