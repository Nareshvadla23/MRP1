package com.member.registration.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerControllerTest {

	@InjectMocks
	ExceptionHandlerController exceptionHandlerController;

	
	@Test
	void testHandlingException() {

		
	}

	@Test
	void testHandleMemberNotFoundException() {
		MemberNotFoundException exception = new MemberNotFoundException("Member Not Found");
		Map<String, String> error = exceptionHandlerController.handleMemberNotFoundException(exception);
		assertEquals("Member Not Found", error.get("errorMessage"));
	}

	@Test
	void testHandleMemberAlreadyExistException() {

		MemberAlreadyExistException exception = new MemberAlreadyExistException("Member Already registered");
		Map<String, String> error = exceptionHandlerController.handleMemberAlreadyExistException(exception);
		assertEquals("Member Already registered", error.get("errorMessage"));

	}

}
