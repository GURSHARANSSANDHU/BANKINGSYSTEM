package com.example.BANKINGSYSTEM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//EXCEPTION HANDLER...........
@ControllerAdvice
public class ExceptionHandle 
{
	
	@ExceptionHandler(UserDefinedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionEntity handleException(UserDefinedException e, HttpServletRequest request,  HttpServletResponse response)
	{
		return new ExceptionEntity(e.getMessage(), request.getRequestURI());
	}

}
