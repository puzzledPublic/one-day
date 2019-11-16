package com.oneday.sofa.domain.member.exception;

import com.oneday.sofa.global.error.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {
	public MemberNotFoundException() {
		super("Member");
	}
}
