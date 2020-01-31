package com.oneday.sofa.domain.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String>{
	
	private int minLen;
	private int maxLen;
	
	@Override
	public void initialize(Username constraintAnnotation) {
		minLen = constraintAnnotation.min();
		maxLen = constraintAnnotation.max();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^[a-zA-Z][\\w\\d]{" + (minLen - 1) + "," + maxLen + "}$");
	}
}
