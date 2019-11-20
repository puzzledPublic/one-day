package com.oneday.sofa.global.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.util.HtmlUtils;

public class XssServletRequestWrapper extends HttpServletRequestWrapper {
	
	public XssServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if(values == null) {
			return null;
		}
		String[] encodedValues = new String[values.length];
		for(int i = 0; i < values.length; i++) {
			encodedValues[i] = HtmlUtils.htmlEscape(values[i]);
		}
		return encodedValues;
	}
	
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(value == null) {
			return null;
		}
		return HtmlUtils.htmlEscape(value);
	}
	
}
