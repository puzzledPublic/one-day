package com.oneday.sofa.domain.comment.exception;

import com.oneday.sofa.global.error.ErrorCode;
import com.oneday.sofa.global.error.exception.BusinessException;

public class NestedCommentException extends BusinessException {
	
	public NestedCommentException() {
		super(ErrorCode.NESTED_COMMENT);
	}
}
