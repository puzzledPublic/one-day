package com.oneday.sofa.global.error.exception;

import com.oneday.sofa.global.error.ErrorCode;

public class UnAuthorizationException extends BusinessException {
	public UnAuthorizationException() {
		super(ErrorCode.UNAUTHORIZED_ACCES);
	}
	
}
