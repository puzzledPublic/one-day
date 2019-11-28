package com.oneday.sofa.domain.member.dto;

import com.oneday.sofa.domain.member.domain.Member;

public class MemberResponse {
	
	private Long id;
	
	private String userName;
	
	private String nickName;
	
	protected MemberResponse() {}
	
	public MemberResponse(Member member) {
		this.id = member.getId();
		this.userName = member.getUserName();
		this.nickName = member.getNickName();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getNickName() {
		return nickName;
	}
}
