package com.example.BANKINGSYSTEM;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//USER DEFINED EXCPETION CLASS.................

@Getter
@Setter
@ToString
@Data
public class UserDefinedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private String message;

	public UserDefinedException() {

	}

	public UserDefinedException(String m) {

		super(m);

	}

}
