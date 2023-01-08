package com.example.BANKINGSYSTEM;

//ENTITY CLASS AT DATAMODEL LAYER..............
public class ExceptionEntity {

	private String message;
	private String path;

	public ExceptionEntity() {

	}

	public ExceptionEntity(String message, String path) {
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

}
