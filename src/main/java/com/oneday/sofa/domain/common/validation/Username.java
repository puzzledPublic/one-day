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
@Constraint(validatedBy=UsernameValidator.class)
public @interface Username {
	
	String message() default "Invalid Username, Username is consist of alphabet, number and start with alphabet. length is 5 ~ 20";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	int min() default 5;
	
	int max() default 20;
}
