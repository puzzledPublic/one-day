package com.oneday.sofa.domain.recommend.exception;

import com.oneday.sofa.global.error.ErrorCode;
import com.oneday.sofa.global.error.exception.BusinessException;

public class AlreadyRecommendedException extends BusinessException {
	public AlreadyRecommendedException() {
		super(ErrorCode.ALREADY_RECOMMENDED);
	}
}
