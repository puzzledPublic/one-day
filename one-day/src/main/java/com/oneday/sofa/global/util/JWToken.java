package com.oneday.sofa.global.util;

public class JWToken {
	private String accessToken;
	
	public JWToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
}
