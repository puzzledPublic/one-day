package com.oneday.sofa.global.error.exception;

import com.oneday.sofa.global.error.ErrorCode;

public class BusinessException extends RuntimeException {
	private ErrorCode errorCode;
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public BusinessException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
