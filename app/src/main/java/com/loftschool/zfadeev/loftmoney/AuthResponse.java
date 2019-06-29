package com.loftschool.zfadeev.loftmoney;

import com.google.gson.annotations.SerializedName;

class AuthResponse {
	
	private String status;
	
	private String id;
	
	@SerializedName("auth_token")
	private String authToken;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(final String status) {
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}
	
	public String getAuthToken() {
		return authToken;
	}
	
	public void setAuthToken(final String authToken) {
		this.authToken = authToken;
	}
}
