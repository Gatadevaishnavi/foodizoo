package com.zosh.response;

import lombok.Data;

@Data
public class AuthResponse {
	
	private String message;
	private String jwt;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	


}
