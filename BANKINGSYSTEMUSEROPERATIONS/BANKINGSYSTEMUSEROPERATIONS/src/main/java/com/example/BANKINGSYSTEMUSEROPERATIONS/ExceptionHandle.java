package com.example.BANKINGSYSTEMUSEROPERATIONS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(UserDefinedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionEntity handleException(HttpServletRequest request, HttpServletResponse response,
			UserDefinedException exception) 
	{
		
		
		System.out.println("CONTROL LANDED INTO EXCEPTION HANDLER");
		return new ExceptionEntity(exception.getMessage(), request.getRequestURI());
	}

}
