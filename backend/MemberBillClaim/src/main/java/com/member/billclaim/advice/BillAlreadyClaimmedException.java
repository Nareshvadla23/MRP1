package com.member.billclaim.advice;

public class BillAlreadyClaimmedException extends Exception {
	private static final long serialVersionUID = 1L;

	public BillAlreadyClaimmedException(String message) {
		super(message);
		
	}

}
