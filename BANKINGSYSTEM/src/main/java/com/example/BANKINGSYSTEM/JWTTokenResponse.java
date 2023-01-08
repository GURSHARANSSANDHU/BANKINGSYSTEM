package com.example.BANKINGSYSTEM;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class JWTTokenResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JWTTokenResponse [token=" + token + "]";
	}

	public JWTTokenResponse(String token) {
		super();
		this.token = token;
	}

	public JWTTokenResponse() {

	}

}
