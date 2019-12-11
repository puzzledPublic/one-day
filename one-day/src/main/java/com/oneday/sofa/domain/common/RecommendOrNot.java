package com.oneday.sofa.domain.common;

import javax.persistence.Embeddable;

@Embeddable
public class RecommendOrNot {
	
	private int liked;
	
	private int hate;
	
	public RecommendOrNot() {
		this.liked = 0;
		this.hate = 0;
	}
	
	public int getLiked() {
		return this.liked;
	}
	
	public int gethate() {
		return this.hate;
	}
	
	public void increase(boolean liked) {
		if(liked) increaseLiked();
		else increaseHate();
	}
	
	public void decrease(boolean liked) {
		if(liked) decreaseLiked();
		else decreaseHate();
	}
	
	private void increaseLiked() {
		if(checkMax(this.liked)) {
			this.liked++;
		}
	}
	
	private void increaseHate() {
		if(checkMax(this.hate)) {
			this.hate++;
		}
	}
	
	private void decreaseLiked() {
		if(checkMin(this.liked)) {
			this.liked--;
		}
	}
	
	private void decreaseHate() {
		if(checkMin(this.hate)) {
			this.hate--;
		}
	}
	
	private boolean checkMax(int count) {
		return count < Integer.MAX_VALUE;
	}
	
	private boolean checkMin(int count) {
		return count > 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hate;
		result = prime * result + liked;
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
		if (liked != other.liked)
			return false;
		return true;
	}
	
	
}
