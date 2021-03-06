package com.oneday.sofa.domain.member.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.oneday.sofa.domain.common.EntityDate;
import com.oneday.sofa.domain.common.validation.Password;
import com.oneday.sofa.domain.common.validation.Username;
import com.oneday.sofa.domain.member.domain.Member;

public class SignUpRequest {
	
	@NotEmpty
	@Username(min=5, max=20)
	private String username;	
	
	@NotEmpty
	@Password(min=5, max=30)
	private String password;
	
	@Email
	private String email;
	
	protected SignUpRequest() { }
	
	SignUpRequest(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String userName) {
		this.username = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Member toEntity(PasswordEncoder passwordEncoder) {
		Member member = new Member(
				this.getUsername(), 
				this.getUsername(), 
				passwordEncoder.encode(this.getPassword()), 
				this.getEmail(),
				new EntityDate(LocalDateTime.now(), LocalDateTime.now()));
		return member;
	}
}
