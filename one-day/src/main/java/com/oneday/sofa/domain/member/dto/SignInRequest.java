package com.oneday.sofa.domain.member.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class SignInRequest {
	
	@NotEmpty
	@Length(min=4, max=30)
	private String userName;
	
	@NotEmpty
	@Length(min=4, max=30)
	private String password;
	
	protected SignInRequest() {}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
}
