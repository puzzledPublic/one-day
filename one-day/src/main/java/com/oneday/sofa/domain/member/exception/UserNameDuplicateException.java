package com.oneday.sofa.domain.member.exception;

import com.oneday.sofa.global.error.ErrorCode;
import com.oneday.sofa.global.error.exception.BusinessException;

public class UserNameDuplicateException extends BusinessException {
	
	public UserNameDuplicateException() {
		super(ErrorCode.USERNAME_DUPLICATION);
	}
}
