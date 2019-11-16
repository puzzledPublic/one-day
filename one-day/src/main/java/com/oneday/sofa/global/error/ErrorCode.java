package com.oneday.sofa.global.error;

public enum ErrorCode {
	
	INVALID_JWT(400, "JWT001", "Invalid JWT"),
	
	NO_HANDLER_FOUND(404, "NHF001", "Not Found"),
	
	USERNAME_DUPLICATION(400, "M001", "Username is already exist"),
	EMAIL_DUPLICATION(400, "M002", "Email is already exist"),
	INCORRECT_PASSWORD(400, "M003", "Password is incorrect"),
	
	UNAUTHORIZED_ACCES(401, "G001", "Unauthorized access"),
	ENTITY_NOT_FOUND(404, "G002", "Entity is not exist"),
	INTERNAL_SERVER_ERROR(500, "G003", "Internal server error happened"),
	;
	
	private int status;
	private String code;
	private String message;
	
	private ErrorCode(int status, String code, String message) {
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
