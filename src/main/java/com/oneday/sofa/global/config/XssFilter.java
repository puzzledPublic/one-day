package com.oneday.sofa.global.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

//TODO:: JSON 입력에 대해서도 XSS 방어 필요
//JSON 내용에 대해서는 xssfilter 적용이 안됨. 
//그래서 JSON 변환을 해주는 HttpMessageConverter 중에 하나인 MappingJackson2HttpMessageConverter을 커스터마이징하여 JSON에 대해 XSS 방어 적용 필요
@Component
public class XssFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		chain.doFilter(new XssServletRequestWrapper((HttpServletRequest)request), response);
	}
	
}
