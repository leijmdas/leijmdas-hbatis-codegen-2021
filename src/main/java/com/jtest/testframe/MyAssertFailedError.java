package com.jtest.testframe;

public class MyAssertFailedError extends Error { // failure Error Error
	// RuntimeException

	private static final long serialVersionUID = 1L;

	public MyAssertFailedError() {
	}

	public MyAssertFailedError(String message) {
		super(message);
	}
}
