package com.debproject.calculator.controllers.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<StandardError> handleAllExceptions(Exception ex, WebRequest request){
		StandardError standardError = new StandardError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(standardError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnsupportedMathOperationException.class)
	public final ResponseEntity<StandardError> handleBadRequestExceptions(Exception ex, WebRequest request){
		StandardError standardError = new StandardError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(standardError, HttpStatus.BAD_REQUEST);
	}
}
