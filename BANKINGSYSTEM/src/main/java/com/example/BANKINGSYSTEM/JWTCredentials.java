package com.example.BANKINGSYSTEM;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Data
public class JWTCredentials {

	private String username;
	private String password;

	public JWTCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public JWTCredentials() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
