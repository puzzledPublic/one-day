package com.oneday.sofa.global.config;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oneday.sofa.domain.common.Role;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.global.error.exception.UnAuthorizationException;
import com.oneday.sofa.global.util.JWTProcessor;
import com.oneday.sofa.global.util.JWToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

@Aspect
@Component
public class JWTAOP {

	private static final Logger log = LoggerFactory.getLogger(JWTAOP.class);

	@Autowired
	private JWTProcessor jwtProcessor;
	
	@Pointcut("@annotation(com.oneday.sofa.global.config.CheckJWT)")	//Method에 적용된 Annotation
	public void annotatedMethod() {}
	
	@Pointcut("@within(com.oneday.sofa.global.config.CheckJWT)")	//Class에 적용된 Annotation
	public void annotatedClass() {}
	
	@Before("execution(public * *(..)) && (annotatedMethod() || annotatedClass())")
	public void checkJWT(JoinPoint joinPoint) throws JwtException {
		//현재 request 컨텍스트
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		//Authorization Header에서 JWT 파싱
		JWToken jwt = JWToken.from(request);
		Jws<Claims> jws = jwtProcessor.parseJWT(jwt);
		
		//JWT에서 정보 추출
		JWTMember jwtMember = new JWTMember(jws);
		
		//요청 권한이 있는지 확인
		checkRole(joinPoint, jwtMember);
		
		//target 메소드에서 정보가 필요한 경우 넘긴다. 
		addClaimsToJoinPoint(joinPoint, jwtMember);
	}
	
	private void checkRole(JoinPoint joinPoint, JWTMember jwtMember) {
		Role requiredRole = getRequiredRole(joinPoint);
		Role requestRole = jwtMember.getRole();
		
		if(!requestRole.hasRole(requiredRole)) {
			log.info("username: " + jwtMember.getUserName() + " requestRole: " + requestRole + ", requiredRole: " + requiredRole);
			throw new UnAuthorizationException();
		}
	}
	
	private Role getRequiredRole(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		
		//Class의 annotation 확인
		@SuppressWarnings("unchecked")
		CheckJWT annotationOnClass = (CheckJWT)methodSignature.getDeclaringType().getAnnotation(CheckJWT.class);
		//Method의 annotation 확인
		CheckJWT annotationOnMethod = methodSignature.getMethod().getAnnotation(CheckJWT.class);
		
		log.info("annotationOnClass: " + annotationOnClass + " annotationOnMethod: " + annotationOnMethod);
		//Method의 annotation에 있는 Role이 우선한다.
		if(annotationOnMethod != null) {
			return annotationOnMethod.value();
		}
		
		return annotationOnClass.value();
	}
	
	private void addClaimsToJoinPoint(JoinPoint joinPoint, JWTMember jwtMember) {
		for(Object obj : joinPoint.getArgs()) {
			if(obj instanceof JWTMember) {
				log.info("username: " + jwtMember.getUserName() + " JWTMember is added to method parameter");
				((JWTMember)obj).copyFrom(jwtMember);;
				break;
			}
		}
	}

}
