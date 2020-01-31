package com.oneday.sofa.global.error.exception;

import com.oneday.sofa.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {
	
	public EntityNotFoundException() {
		super("Entity", ErrorCode.ENTITY_NOT_FOUND);
	}
	
	public EntityNotFoundException(String message) {
		super(message, ErrorCode.ENTITY_NOT_FOUND);
	}
}
