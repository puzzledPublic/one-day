package com.oneday.sofa.domain.member.dto;

import com.oneday.sofa.domain.common.Role;

import io.jsonwebtoken.Claims;

public class JWTMember {
	private Long id;
	private String userName;
	private Role role;
	
	protected JWTMember() {}
	
	public JWTMember(Long id, String userName, Role role) {
		this.id = id;
		this.userName = userName;
		this.role = role;
	}
	
	public JWTMember(Claims claims) {
		this.id = claims.get("id", Long.class);
		this.userName = claims.get("username", String.class);
		this.role = Role.valueOf(claims.get("role", String.class));
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void copyFrom(JWTMember jwtMember) {
		this.id = jwtMember.getId();
		this.userName = jwtMember.getUserName();
		this.role = jwtMember.getRole();
	}
}
