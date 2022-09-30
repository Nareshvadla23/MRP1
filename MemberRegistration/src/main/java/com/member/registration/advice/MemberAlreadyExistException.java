package com.member.registration.advice;

public class MemberAlreadyExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public MemberAlreadyExistException(String message) {
		super(message);
	}

}
