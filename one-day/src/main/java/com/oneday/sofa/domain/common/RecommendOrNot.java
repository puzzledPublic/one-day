package com.oneday.sofa.domain.common;

import javax.persistence.Embeddable;

@Embeddable
public class RecommendOrNot {
	
	private int recommend;
	
	private int hate;
	
	protected RecommendOrNot() {}
	
	public RecommendOrNot(int recommend, int hate) {
		this.recommend = recommend;
		this.hate = hate;
	}
	
	public int getrecommend() {
		return recommend;
	}
	
	public int gethate() {
		return hate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hate;
		result = prime * result + recommend;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecommendOrNot other = (RecommendOrNot) obj;
		if (hate != other.hate)
			return false;
		if (recommend != other.recommend)
			return false;
		return true;
	}
	
	
}
