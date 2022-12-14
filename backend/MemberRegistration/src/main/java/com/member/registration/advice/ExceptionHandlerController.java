package com.member.registration.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(ExceptionHandlerController.class);
 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handlingException(MethodArgumentNotValidException exception) {
		logger.info("MethodArgumentNotValidException method Acessed");
		Map<String, String> error = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(err -> 
			error.put(err.getField(), err.getDefaultMessage())
		);
		return error;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MemberNotFoundException.class)
	public Map<String, String> handleMemberNotFoundException(MemberNotFoundException ex) {
		logger.info("MemberNotFoundException method Acessed");
		Map<String, String> error = new HashMap<>();
		error.put("errorMessage", ex.getMessage());
		return error;
	} 
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MemberAlreadyExistException.class)
	public Map<String, String> handleMemberAlreadyExistException(MemberAlreadyExistException ex) {
		logger.info("MemberAlreadyExistException method Acessed");
		Map<String, String> error = new HashMap<>();
		error.put("errorMessage", ex.getMessage());
		return error;

	}

}
