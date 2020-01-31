package com.oneday.sofa.global.error;

public class ErrorResponse {
	
	private int status;
	private String code;
	private String message;
	
	protected ErrorResponse() {}
	
	public ErrorResponse(ErrorCode errorCode) {
		this.status = errorCode.getStatus();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}
	
	public ErrorResponse(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	public int getStatus() {
		return status;
	}
		
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
}
