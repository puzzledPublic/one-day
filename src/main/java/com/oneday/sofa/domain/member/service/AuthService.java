package com.oneday.sofa.domain.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oneday.sofa.domain.member.domain.Member;
import com.oneday.sofa.domain.member.dto.SignInRequest;
import com.oneday.sofa.domain.member.exception.IncorrectPasswordException;
import com.oneday.sofa.global.util.JWTProcessor;
import com.oneday.sofa.global.util.JWToken;

@Service
public class AuthService {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JWTProcessor jwtProcessor;
	
	//로그인
	public JWToken signIn(SignInRequest signInDTO) {
		Member member = memberService.findMemberByUserName(signInDTO.getUsername());
		
		if(!member.matchPassword(passwordEncoder, signInDTO.getPassword())) {
			throw new IncorrectPasswordException();
		}
		
		return jwtProcessor.createJWT(member);			
	}
	
}
