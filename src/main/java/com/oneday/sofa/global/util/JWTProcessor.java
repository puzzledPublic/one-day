package com.oneday.sofa.global.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.oneday.sofa.domain.member.domain.Member;
import com.oneday.sofa.global.error.exception.UnAuthorizationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTProcessor {
	
	private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public JWToken createJWT(Member member) {
		String jwt = Jwts.builder()
					.setIssuer("oneday")
					.setSubject(member.getUserName())
					.setClaims(member.getJWTClaims())
					.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)))
					.setIssuedAt(new Date())
					.signWith(secretKey)
					.compact();
		
		return new JWToken(jwt);
	}
	
	public Jws<Claims> parseJWT(JWToken jwt) {
		try {
			return Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(jwt.getAccessToken());
			
		}catch(IllegalArgumentException e) {
			throw new UnAuthorizationException(); 
		}catch(JwtException e) {
			throw e;
		}
	}
}
