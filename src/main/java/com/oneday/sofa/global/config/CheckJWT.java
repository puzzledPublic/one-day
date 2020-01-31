package com.oneday.sofa.global.config;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.oneday.sofa.domain.common.Role;

/**
 * Http 요청으로부터 JWToken을 확인하는 AOP Annotation.
 * HttpServletRequest를 갖는 Context 내의 class, method에 붙일 수 있다.
 * 
 * HTTP Authorization Header의 Bearer Token 값을 파싱 후 요청 권한을 확인한다.
 * 이 Annotation을 붙인 Method Parameter에 JWTMember가 있다면 Token 내용을 JWTMember에 저장한다.
 * 
 * {@link JWTAOP}
 * com.oneday.sofa.global.config.JWTAOP 참조
 */
@Retention(RUNTIME)
@Target({ METHOD, TYPE })
public @interface CheckJWT {
	Role value() default Role.USER;
}
