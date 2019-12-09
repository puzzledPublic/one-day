package com.oneday.sofa.global.error;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oneday.sofa.global.error.exception.BusinessException;

import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//Override 안한 ResponseEntityExceptionHandler Exception들의 최종 핸들러
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error("Concerned with RequestMapping Error", ex);
		ErrorResponse errorResponse = new ErrorResponse(status.value(), "GLO001", ex.getMessage());
		return new ResponseEntity<>(errorResponse, status);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> errors = bindingResult.getFieldErrors();
		StringBuilder sb = new StringBuilder();
		for(FieldError fe : errors) {
			sb.append(fe.getField() + ", " + fe.getDefaultMessage() + "\n");
		}
		ErrorResponse errorResponse = new ErrorResponse(400, "GLO002", sb.toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> errors = bindingResult.getFieldErrors();
		StringBuilder sb = new StringBuilder();
		for(FieldError fe : errors) {
			sb.append(fe.getField() + ", " + fe.getDefaultMessage() + "\n");
		}
		ErrorResponse errorResponse = new ErrorResponse(400, "GLO003", sb.toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error("No handler Found Error", ex);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NO_HANDLER_FOUND);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	//Pathvariable, requestParam.. vaildation 예외
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		StringBuilder sb = new StringBuilder();
		for(ConstraintViolation<?> violation : violations) {
			sb.append(violation.getMessage());
		}
		log.error(sb.toString(), ex);
		ErrorResponse errorResponse = new ErrorResponse(400, "GLO004", sb.toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	//업로드 파일 크기 초과
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ErrorResponse> handleMaxUploadExceededException(MaxUploadSizeExceededException ex) {
		log.error("Uploaded file size is Exceeded", ex);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.UPLOAD_FILE_SIZE_EXCEED);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	//JWT 예외
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ErrorResponse> handleInvalidJWT(JwtException ex) {
		log.error("JWT Error", ex);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_JWT);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	//비즈니스 예외
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
		log.error("Business Error", ex);
		ErrorCode errorCode = ex.getErrorCode();
		ErrorResponse errorResponse = new ErrorResponse(errorCode);
		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleFallbackException(Exception ex) {
		log.error("Internal Server Error", ex);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
