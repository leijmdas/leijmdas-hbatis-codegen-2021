package com.jtest.utility.python;

import com.jtest.annotation.JTest;
import com.jtest.utility.python.checker.PyChecker;
import com.jtest.utility.python.checker.PyError;
import com.jtest.utility.python.checker.PyParser;
import com.jtest.utility.python.stmt.StmtMngr;
import com.jtest.utility.python.stmt.functionstmt.FunctionStatement;
import com.jtest.testframe.ITestFixture;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class TestFunctionStmt extends ITestFixture {
	PyParser pyParser;

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		pyParser = new PyParser();
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}

	@JTest
	public void tstIFStmt() {
		StringWriter sw = new StringWriter(120);
		sw.write("def main():\n");
		sw.write("\tif 1== 1 : \n");
		sw.write(" \n");
		sw.write("\t\tret=1\n");
		sw.write("\t\tret=ret+1\n");
		sw.write("\tif 2== 1 : \n");
		sw.write("\t\tret=1\n");
		sw.write("\t\tret=ret+1\n");
		sw.write("\tif 3== 1 : \n");
		sw.write("\t\tret='rggg'\n");
		sw.write("\t\tret=ret+1\n");
		sw.write("\treturn ret\n");
		String lines = sw.toString();

		PyParser.printLines(lines);

		PyError pyerror = PyChecker.check(lines);
		assertTrue(pyerror.toString(), pyerror.isOk());
		assertTrue(StmtMngr.getIfStmtList() != null);
		assertEquals(3, StmtMngr.getIfStmtList().size());
		StmtMngr.printToString();

	}

	@JTest
	public void testMatchFunctionStmt() {

		String line = " get1(getSub(1)+getCust(2) + (3*4)*5) + get2(l) + get3() ";
		String[] lines = { line };
		FunctionStatement stmt = new FunctionStatement("line");
		try {
			stmt.parseStmt(lines, 0);
		} catch (PyError e) {
			e.printStackTrace();
		}
		stmt.printFnList();
	}

	// @TestIgnore
	@JTest
	public void parseStmt() {
		String line = " getAAAAA(getSub(1)+getCust(2) + (3*4)*5) + get2(l) + get3() ";
		String[] lines = { line };
		try {
			FunctionStatement stmt = new FunctionStatement("line");
			stmt.parseStmt(lines, 0);
			stmt.printFnList();
		} catch (PyError e) {
			e.printStackTrace();
		}
	}

	@JTest
	public void testList2Array() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		String[] s = new String[2];
		list.toArray(s);
		for (String item : s) {
			System.out.println(item);
		}
		System.out.println(s.toString());
	}

	@JTest
	public void tstIFStmt_Elif() {
		StringWriter sw = new StringWriter(120);
		sw.write("def main():\n");
		sw.write("\tif 1== 1 : \n");
		sw.write(" \n");
		sw.write("\t\tret=1\n");
		sw.write("\t\tret=ret+1\n");
		sw.write("\tif 2== 1 : \n");
		sw.write("\t\tret=1\n");
		sw.write("\t\tret=ret+1\n");
		sw.write(" \n");
		sw.write("\telif 3== 1 : \n");
		sw.write(" \n");
		sw.write("\t\tret='rggg'\n");
		sw.write("\t\tret=ret+1\n");
		sw.write("\telif 3== 1 : \n");
		sw.write(" \n");
		sw.write("\t\tret='rggg'\n");
		sw.write("\t\tret=ret+1\n");

		sw.write("\treturn ret\n");
		String lines = sw.toString();

		PyParser.printLines(lines);

		PyError pyerror = PyChecker.check(lines);
		assertTrue(pyerror.toString(), pyerror.isOk());
		assertTrue(StmtMngr.getIfStmtList() != null);
		assertEquals(2, StmtMngr.getIfStmtList().size());
		StmtMngr.printToString();

	}

}
