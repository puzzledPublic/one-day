package com.oneday.sofa.domain.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oneday.sofa.domain.member.dao.MemberRepository;
import com.oneday.sofa.domain.member.domain.Member;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.domain.member.dto.SignUpRequest;
import com.oneday.sofa.domain.member.exception.EmailDuplicateException;
import com.oneday.sofa.domain.member.exception.MemberNotFoundException;
import com.oneday.sofa.domain.member.exception.UserNameDuplicateException;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void signUp(SignUpRequest signUpDTO) {
		
		if(memberRepository.existsByUserName(signUpDTO.getUserName())) {
			throw new UserNameDuplicateException();
		}
		
		if(memberRepository.existsByEmail(signUpDTO.getEmail())) {
			throw new EmailDuplicateException();
		}
		
		Member member = signUpDTO.toEntity(passwordEncoder);
		
		memberRepository.save(member);
	}
	
	public void deleteMember(JWTMember jwtMember) {
		memberRepository.deleteById(jwtMember.getId());
	}
	
	public Member findMemberById(long memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
		return member;
	}
}
