package com.oneday.sofa.domain.member.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.oneday.sofa.domain.common.validation.Username;

public class SignInRequest {
	
	@NotEmpty
	@Username(min=5, max=20)
	private String username;
	
	@NotEmpty
	@Length(min=4, max=30)
	private String password;
	
	protected SignInRequest() {}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
