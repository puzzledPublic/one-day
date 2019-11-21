package com.oneday.sofa.domain.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsernameValidator implements ConstraintValidator<Username, String>{
	
	private int minLen;
	private int maxLen;
	
	
	private static final Logger log = LoggerFactory.getLogger(UsernameValidator.class);

	@Override
	public void initialize(Username constraintAnnotation) {
		minLen = constraintAnnotation.min();
		maxLen = constraintAnnotation.max();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		log.info(value);
		return value.matches("^[a-zA-Z][\\w\\d]{" + (minLen - 1) + "," + maxLen + "}$");
	}
}
