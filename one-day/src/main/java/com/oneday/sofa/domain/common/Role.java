package com.oneday.sofa.domain.common;

public enum Role {
	ADMIN(4, null),
	MANAGER(3, ADMIN),
	USER(2, MANAGER),
	GUEST(1, USER),
	;
	
	private int value;
	private Role nextRole;
	
	private Role(int value, Role nextRole) {
		this.value = value;
		this.nextRole = nextRole;
	}
	
	public boolean hasRole(Role requiredRole) {
		return this.value >= requiredRole.value;
	}
	
}
