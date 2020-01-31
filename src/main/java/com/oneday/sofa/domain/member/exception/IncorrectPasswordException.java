package com.oneday.sofa.domain.member.exception;

import com.oneday.sofa.global.error.ErrorCode;
import com.oneday.sofa.global.error.exception.BusinessException;

public class IncorrectPasswordException extends BusinessException {
	public IncorrectPasswordException() {
		super(ErrorCode.INCORRECT_PASSWORD);
	}
}
