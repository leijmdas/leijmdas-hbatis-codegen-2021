package com.jtest.utility.python.stmt;


import com.jtest.utility.python.checker.PyContext;
import com.jtest.utility.python.checker.PyError;
import com.jtest.utility.python.stmt.ifstmt.IFStatement;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class StmtMngr {
	static List<IStatement> stmtList = new ArrayList<IStatement>();
	static List<IFStatement> ifStmtList = new ArrayList<IFStatement>();
	static List<AssignStatement> assignStmtList = new ArrayList<AssignStatement>();

	public static boolean isIFStmt(String line) {
		return IFStatement.isIFStmt(line);
	}

	public static boolean isIFStmtInvalid(String line) throws PyError {
		return IFStatement.isIFStmtInvalid(line);
	}

	public static int newDefaultStmt(String[] lines, int istartline) {
		String line = lines[istartline];
		stmtList.add(new DefaultStatement(line, istartline, istartline));
		return istartline + 1;

	}

	public static void parse(PyContext ctx) throws PyError {
		// context = context;
		// assert ctx == null;
		String lines[] = ctx.getLines();
		for (int i = 0; i < lines.length; i++) {
			i = parse(lines, i);
		}
	}

	// return lineno
	public static int parse(String[] lines, int istartline) throws PyError {
		if (StmtMngr.isIFStmtInvalid(lines[istartline])) {
			String msg = String
					.format("\n ifStmt start as elif  or else is Invalid! lineno="
							+ (istartline + 1) + lines[istartline]);
			throw new PyError(msg, istartline, null);
		} else if (StmtMngr.isIFStmt(lines[istartline])) {
			IFStatement ifstmt = StmtMngr.newIfStmt(lines[istartline],
					istartline, istartline);
			try {
				if (ifstmt.checkStmt(lines, istartline)) {
					try {
						istartline = ifstmt.parseStmt(lines, istartline);
					} catch (PyError e) {
						e.printStackTrace();
						throw e;
					} catch (Exception e) {
						e.printStackTrace();
					}
					ifstmt.printToString();
				} else {
					throw new PyError(" ifstmt.checkStmt failed!", istartline,
							null);
				}
			} catch (PyError e) {
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (AssignStatement.isAssignStmt(lines[istartline])) {
			stmtList.add(new AssignStatement(lines[istartline], istartline,
					istartline));
		} else {
			newDefaultStmt(lines, istartline);
		}
		return istartline;
	}

	public static void clear() {
		ifStmtList.clear();
		assignStmtList.clear();
		stmtList.clear();
	}

	public static void addIfStmt(IFStatement ifstmt) {
		ifStmtList.add(ifstmt);
	}

	public static void addAssignStmt(AssignStatement stmt) {
		assignStmtList.add(stmt);
	}

	public static IFStatement newIfStmt(String line, int start, int endline) {
		IFStatement ifstmt = new IFStatement(line, start, endline);
		stmtList.add(ifstmt);
		ifStmtList.add(ifstmt);
		return ifstmt;
	}

	public static List<IFStatement> getIfStmtList() {
		return ifStmtList;
	}

	public static void printToString() {
		System.out.println(_toString());
	}

	public static String _toString() {
		StringWriter sw = new StringWriter(120);
		for (IStatement stmt : stmtList) {
			sw.write(stmt.toString());
		}
		return sw.toString();
	}

}
