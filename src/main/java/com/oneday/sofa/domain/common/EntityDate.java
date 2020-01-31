package com.oneday.sofa.domain.common;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Embeddable
public class EntityDate {
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	public EntityDate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	public EntityDate(LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void updateDate() {
		this.updatedAt = LocalDateTime.now();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		EntityDate other = (EntityDate) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}
	
	
}
