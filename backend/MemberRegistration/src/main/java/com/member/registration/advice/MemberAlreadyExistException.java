package com.member.registration.advice;

public class MemberAlreadyExistException extends Exception {
	private static final long serialVersionUID = 2114440960370937566L;

	public MemberAlreadyExistException(String message) {
		super(message);
	}

}
