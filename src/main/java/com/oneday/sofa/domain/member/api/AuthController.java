package com.oneday.sofa.domain.member.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneday.sofa.domain.member.dto.SignInRequest;
import com.oneday.sofa.domain.member.exception.AlreadySignInException;
import com.oneday.sofa.domain.member.service.AuthService;
import com.oneday.sofa.global.util.JWToken;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signin")
	public JWToken signIn(@RequestBody @Valid SignInRequest signInDTO, HttpServletRequest request) {
		
		JWToken jwt = JWToken.from(request);
		if(jwt.getAccessToken() != null) {
			throw new AlreadySignInException();
		}
		
		return authService.signIn(signInDTO);
	}
	
}
