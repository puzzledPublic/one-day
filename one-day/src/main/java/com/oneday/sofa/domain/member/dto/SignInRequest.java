package com.oneday.sofa.domain.member.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.oneday.sofa.domain.common.validation.Username;

public class SignInRequest {
	
	@NotEmpty
	@Username(min=5, max=20)
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
