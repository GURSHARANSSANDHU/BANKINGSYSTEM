package com.example.BANKINGSYSTEMUSEROPERATIONS;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class ExceptionEntity {

	private String message;
	private String path;

	public ExceptionEntity() {

		// TODO Auto-generated constructor stub
	}

	public ExceptionEntity(String message, String path) {
		super();
		this.message = message;
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ExceptionEntity [message=" + message + ", path=" + path + "]";
	}

}
