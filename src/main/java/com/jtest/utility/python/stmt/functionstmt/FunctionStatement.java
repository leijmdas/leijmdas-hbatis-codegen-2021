package com.jtest.utility.python.stmt.functionstmt;

import com.jtest.utility.python.checker.PyContext;
import com.jtest.utility.python.checker.PyError;
import com.jtest.utility.python.stmt.IStatement;

import java.util.Vector;
import java.util.regex.Pattern;

public class FunctionStatement extends IStatement {

	static Vector<FunctionStatement> fnList = new Vector<FunctionStatement>();
	Vector<FunctionStatement> fnSubList = new Vector<FunctionStatement>();

	public FunctionStatement(String line, int startline, int endline) {
		super(line, startline, endline);
		// TODO Auto-generated constructor stub
	}

	public FunctionStatement(PyContext ctx) {
		super();
		context = ctx;
	}

	public FunctionStatement(String fnname) {
		super();
		_functionName = fnname;
	}

	String _functionName;

	public static String matchFunction(String line) {
		Pattern p = Pattern.compile("\\w+\\(.*\\)");
		java.util.regex.Matcher m = p.matcher(line);
		if (m.find()) {
			// System.err.println(m.group());
			return m.group();
		}
		return "";
	}

	public static void matchFunctions(String line, FunctionStatement parntFn) {
		int start = 0;
		int count = 0;
		int startparam = 0;
		boolean hasFn = false;
		String fn = null;
		String fnParam = null;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '(') {
				if (count == 0) {
					startparam = i;
				}
				count++;
				hasFn = true;
			}

			if (line.charAt(i) == ')') {
				count--;
			}
			if (count == 0 && hasFn) {
				hasFn = false;
				fn = line.substring(start, i + 1);
				fnParam = line.substring(startparam, i + 1);
				fn = matchFunction(fn).replace(fnParam, "").trim();
				fnParam = line.substring(startparam + 1, i).trim();
				System.err.println("fn=\t" + fn + "\t\tparam=" + fnParam);
				FunctionStatement fnstmt = new FunctionStatement(fn);
				if (null == parntFn) {
					fnList.add(fnstmt);
				} else {
					parntFn.fnSubList.add(fnstmt);
				}
				matchFunctions(fnParam, fnstmt);
				start = i + 1;
			}
		}
	}

	public void printFnList() {
		for (FunctionStatement stmt : fnList) {
			System.err.println("PRINT==" + stmt._functionName);
			for (FunctionStatement substmt : stmt.fnSubList) {
				System.err.println("\t SUB==" + substmt._functionName);
			}
		}

	}

	public int parseStmt() throws PyError {
		String[] lines = context.getLines();

		for (int i = 0; i < lines.length; i++) {

			matchFunctions(lines[i], null);
		}
		return 0;
	}

	@Override
	public int parseStmt(String[] lines, int istart) throws PyError {
		matchFunctions(lines[istart], null);
		return 0;
	}

}
