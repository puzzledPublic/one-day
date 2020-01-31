package com.oneday.sofa.global.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

public class JWToken {
	private String accessToken;
	
	public JWToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public static JWToken from(HttpServletRequest request) {
		String authHeaderString = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authHeaderString != null) {
			String[] authHeaderValues = authHeaderString.split(" ");
			for(int i = 0; i < authHeaderValues.length; i++) {
				if("bearer".equals(authHeaderValues[i].toLowerCase()) && (i + 1) < authHeaderValues.length) {
					return new JWToken(authHeaderValues[i + 1]);
				}
			}
		}

		return new JWToken(null);
	}
}
