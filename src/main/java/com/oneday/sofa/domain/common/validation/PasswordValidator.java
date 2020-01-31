package com.oneday.sofa.domain.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String>{

	private int minLen;
	private int maxLen;
	
	@Override
	public void initialize(Password constraintAnnotation) {
		minLen = constraintAnnotation.min();
		maxLen = constraintAnnotation.max();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("[a-zA-Z0-9!@#$%^&*]{" + minLen + "," + maxLen + "}$");
	}
}
