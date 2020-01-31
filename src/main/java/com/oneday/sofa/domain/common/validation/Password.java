package com.oneday.sofa.domain.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ TYPE, FIELD })
@Constraint(validatedBy=PasswordValidator.class)
public @interface Password {
	
	String message() default "Invalid Password, Password is consist of alphabet, number and special character(!@#$%^&*). Length is 5 ~ 30";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	int min() default 5;
	
	int max() default 30;
}
