package com.jtest.utility.python.stmt;


import com.jtest.utility.testlog.TestLog;
import com.jtest.utility.python.checker.PyError;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssignStatement extends IStatement {
	public static ArrayList<String> varList = new ArrayList<String>();

	public AssignStatement() {
		this._startno = 0;
		this._endno = 0;
		this._ft = StatementType.ASSIGN;
		this._lines = new String[1];
	}

	public AssignStatement(String line, int startline, int endline) {
		super(line, startline, endline);
	}

	public static boolean isExist(String line, String reg) {

		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(line);
		return m.find();
	}

	public static boolean isAssignStmt(String line) {
		String[] pvar = new String[1];
		return isAssignStmt(line, pvar);
	}

	public static boolean isAssignStmt(String line, String[] pvar) {
		Pattern p = Pattern.compile("\\w*\\s*.{1}\\=+.*");
		Matcher m = p.matcher(line);
		while (m.find()) {
			String stoken = m.group();
			System.out.println(stoken);
			if (stoken.indexOf("==") >= 0 || stoken.indexOf("!=") >= 0
					|| stoken.indexOf(">=") >= 0 || stoken.indexOf("<=") >= 0) {
				return false;
			}
			String st[] = stoken.split("=");
			pvar[0] = st[0].trim();
			getList().add(pvar[0]);
			return true;
		}

		return false;
	}

	public static ArrayList<String> getList() {
		return varList;
	}

	public static void printList() {
		System.out.println("varList def is:\t");
		for (String var : varList) {
			System.out.println(var);
		}

	}

	public static void logList() {
		TestLog.log("varList def is:\t");
		for (String var : varList) {
			TestLog.log(var);
		}

	}

	public static void setList(ArrayList<String> list) {
		AssignStatement.varList = list;
	}

	@Override
	public int parseStmt(String[] lines, int ilineno) throws PyError {

		return 0;
	}

}
