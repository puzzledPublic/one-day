package com.oneday.sofa.domain.member.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneday.sofa.domain.common.Role;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.domain.member.dto.SignUpRequest;
import com.oneday.sofa.domain.member.service.MemberService;
import com.oneday.sofa.global.config.CheckJWT;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	
	@CheckJWT(Role.ADMIN)
	@GetMapping
	public String member(JWTMember jwtMember) {
		log.info(jwtMember.getId() + " " + jwtMember.getUserName() + " " + jwtMember.getRole());
		return "member";
	}
	
	//회원 생성
	@PostMapping
	public void create(@RequestBody @Valid SignUpRequest signUpDTO) {
		memberService.signUp(signUpDTO);
	}
}
