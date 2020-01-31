package com.oneday.sofa.domain.member.exception;

import com.oneday.sofa.global.error.ErrorCode;
import com.oneday.sofa.global.error.exception.BusinessException;

public class EmailDuplicateException extends BusinessException {
	public EmailDuplicateException() {
		super(ErrorCode.EMAIL_DUPLICATION);
	}
}
