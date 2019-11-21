package com.oneday.sofa.domain.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileValidator implements ConstraintValidator<UploadFile, MultipartFile>{
	
	
	private static final Logger log = LoggerFactory.getLogger(UploadFileValidator.class);

	@Override
	public void initialize(UploadFile constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		
		log.info("uploaded origin file name" + value.getOriginalFilename() + " " + value.getContentType());
		
		String originalFileName = value.getOriginalFilename();
		String contentType = value.getContentType();
		
		if(originalFileName.trim().matches("^(.*?)\\.(jpg|png|jpeg|gif)$") && contentType.trim().matches("^image\\/(jpg|png|jpeg|gif)$")) {
			return true;
		}
		
		return false;
	}
}
