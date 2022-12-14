package com.member.billclaim.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handlingException(MethodArgumentNotValidException exception) {
		Map<String, String> error = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(err -> 
			error.put(err.getField(), err.getDefaultMessage())
		);
		return error;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MemberNotFoundException.class)
	public Map<String, String> handleMemberNotFoundException(MemberNotFoundException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("errorMessage", ex.getMessage());
		return error;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BillAlreadyClaimmedException.class)
	public Map<String, String> handleBillAlreadyClaimmedException(BillAlreadyClaimmedException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("errorMessage", ex.getMessage());
		return error;
	}

}
