package com.jtest.utility.python.checker;


import com.jtest.utility.testlog.TestLog;
import com.jtest.utility.python.stmt.AssignStatement;
import com.jtest.utility.python.stmt.StmtMngr;
import com.jtest.utility.python.stmt.functionstmt.FunctionStatement;
import com.jtest.utility.python.stmt.ifstmt.IFStatement;

import java.util.Set;
import java.util.TreeSet;

public class PyParser extends PyObject {
	static Set<IFStatement> iFStmtSet = new TreeSet<IFStatement>();
	static Set<AssignStatement> assignStmtSet = new TreeSet<AssignStatement>();

	public static void clear() {
		StmtMngr.clear();
	}

	public void setLines(String script) {
		context.setScripts(script);
	}

	public void parseAssignStatement() throws PyError {
		String lines[] = context.getLines();
		for (int i = 0; i < context.getLines().length; i++) {
			String[] pt = new String[1];
			if (AssignStatement.isAssignStmt(lines[i], pt)) {
				AssignStatement.printList();
				StmtMngr.addAssignStmt(new AssignStatement());
			}

		}

	}

	public void parse(PyContext ctx) throws PyError {

		clear();
		this.context = ctx;

		new FunctionStatement(ctx).parseStmt();
		parseAssignStatement();
		PyToken.parseWordsUndef(ctx);
		StmtMngr.parse(ctx);

	}

	public static void printLines(String sline) {
		String lines[] = sline.split("\n");
		for (int i = 0; i < lines.length; i++) {
			System.out.printf("%d\t %s\n", i + 1, lines[i]);
			// MyLog.log(String.format("%d\t %s\n", i + 1, lines[i]));
		}
	}

	public static void logLines(String slines) {
		String[] lines = slines.split("\n");
		for (String line : lines) {
			TestLog.log(String.format(" %s\n", line));
		}
	}
}
