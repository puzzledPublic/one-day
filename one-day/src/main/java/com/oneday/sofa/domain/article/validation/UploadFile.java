package com.oneday.sofa.domain.article.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * 업로드 된 파일(MultipartFile)이 이미지 확장자, MIME 타입을 갖는지 검사
 *
 */

@Retention(RUNTIME)
@Target({ TYPE, FIELD, TYPE_USE })
@Constraint(validatedBy=UploadFileValidator.class)
public @interface UploadFile {
	
	String message() default "Upload File validation is fail";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
