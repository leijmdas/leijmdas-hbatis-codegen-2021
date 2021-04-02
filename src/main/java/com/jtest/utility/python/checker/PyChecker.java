package com.jtest.utility.python.checker;

public class PyChecker {
	static PyParser pyParser = null;

	public static PyError check(String scripts) {
		PyContext ctx = new PyContext(scripts);

		try {
			ctx.check();
			checkFirst();
			pyParser = new PyParser();
			pyParser.parse(ctx);
			checkLast();
		} catch (PyError e) {
			e.printStackTrace();
			return e;
		}
		return new PyError();
	}

	public static void checkFirst() throws PyError {

	}

	public static void checkLast() throws PyError {

	}

	public static void checkBody() throws PyError {

	}

}
