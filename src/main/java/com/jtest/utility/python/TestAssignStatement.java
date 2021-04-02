package com.jtest.utility.python;

import com.jtest.utility.python.checker.PyError;
import com.jtest.utility.python.checker.PyParser;
import com.jtest.utility.python.checker.PyToken;
import com.jtest.utility.python.stmt.AssignStatement;
import com.jtest.testframe.ITestFixture;

import java.io.StringWriter;

public class TestAssignStatement extends ITestFixture {
	PyParser pyParser = null;

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		pyParser = new PyParser();
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}

	// =
	public void isAssign() {
		/*
		 * Pattern p = Pattern.compile("\\w*\\s*\\=+\\s*"); Matcher m =
		 * p.matcher(line); while (m.find()) { String stoken = m.group(); if
		 * (stoken.indexOf("==") < 0) { String st[] = stoken.split("=");
		 * ptoken[0] = st[0]; System.out.println("====isAssign: " + ptoken[0]);
		 * } return true; } return false;
		 */
		boolean b = AssignStatement.isAssignStmt("itn   == aadd");
		assertTrue("==", !b);

		b = AssignStatement.isAssignStmt("itn   >= aadd");
		assertTrue(">=", !b);
		b = AssignStatement.isAssignStmt("itn   <= aadd");
		assertTrue("<=", !b);
		b = AssignStatement.isAssignStmt("itn !=  aadd");
		assertTrue("!=", !b);
		b = AssignStatement.isAssignStmt("itn   aadd");
		assertTrue(" ", !b);

		b = AssignStatement.isAssignStmt("itn =  aadd");
		assertTrue("=", b);

	}

	public void testIsType() {
		assertTrue(PyToken.isConst("111"));
		assertTrue(PyToken.isToken("return"));
	}

	public void testIsToken() {
		StringWriter sw = new StringWriter(120);
		sw.write("def main():\n");
		sw.write("ret=1 \n");
		sw.write("aa=111l \n");

		sw.write("\treturn ret\n");
		String lines = sw.toString();
		PyParser.printLines(lines);
		pyParser.setLines(lines);
		try {
			pyParser.parseAssignStatement();
		} catch (PyError e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
		// assertTrue(pyerror.toString(), pyerror.isOk());
	}

}
