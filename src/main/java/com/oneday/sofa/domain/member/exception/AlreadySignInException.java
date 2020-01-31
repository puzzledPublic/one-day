package com.oneday.sofa.domain.member.exception;

import com.oneday.sofa.global.error.ErrorCode;
import com.oneday.sofa.global.error.exception.BusinessException;

public class AlreadySignInException extends BusinessException {
	public AlreadySignInException() {
		super(ErrorCode.ALREADY_SIGN_IN);
	}
}
