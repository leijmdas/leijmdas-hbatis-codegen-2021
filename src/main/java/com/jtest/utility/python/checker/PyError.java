package com.jtest.utility.python.checker;

public class PyError extends Exception {
	public enum ErrorType {
		NoneError, NameError, SyntaxError
	}

	ErrorType errorType = ErrorType.NoneError;

	int _lineno = 0;
	int start;
	int end;
	String _line = "";
	String _errorMsg = "";

	public String pyfilename = null;

	public PyError() {
		errorType = ErrorType.NoneError;
	}

	public boolean isOk() {
		return errorType == ErrorType.NoneError;
	}

	public PyError(String emsg, int lineno, ErrorType b) {
		super(emsg);
		_errorMsg = emsg;
		this.errorType = b;
		_lineno = lineno;
	}

	public PyError(String emsg, int lineno) {
		super(emsg);
		_errorMsg = emsg;
		_lineno = lineno;
	}

	@Override
	public String toString() {
		return "\n\t\tline  " + _lineno + " \t" + _errorMsg;
	}
}
