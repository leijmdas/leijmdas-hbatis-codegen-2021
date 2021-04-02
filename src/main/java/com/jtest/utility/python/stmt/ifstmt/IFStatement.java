package com.jtest.utility.python.stmt.ifstmt;


import com.jtest.utility.python.checker.PyError;
import com.jtest.utility.python.stmt.IStatement;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class IFStatement extends IIFStatement {
	List<IIFStatement> branches = new ArrayList<IIFStatement>();

	public IFStatement(String line, int startline, int endline) {
		super(line, startline, endline);
	}

	public IFStatement() {
		super(IStatement.StatementType.IF);
	}

	public IFStatement(IStatement.StatementType ft) {
		super(ft);
	}

	public int countBranches() {

		return branches.size();

	}

	public int countElifBranches() {
		int cnt = 0;
		for (IIFStatement iif : branches) {
			if (iif.get_ft() == IIFStatement.StatementType.ELIF) {
				cnt++;
			}
		}
		return cnt;

	}

	public int countElseBranches() {
		int cnt = 0;
		for (IIFStatement iif : branches) {
			if (iif.get_ft() == IIFStatement.StatementType.ELSE) {
				cnt++;
			}
		}
		return cnt;

	}

	public static boolean isIFStmt(String line) {
		return isExist(line, "\\s+if\\s{1}");
	}

	public static boolean isIFStmtInvalid(String line) throws PyError {
		if (isExist(line, "\\s+elif\\s{1}|\\s+else\\:")) {
			throw new PyError("isIFStmtInvalid", 1, PyError.ErrorType.NameError);
		}
		return false;
	}

	@Override
	public boolean checkStmt(String[] lines, int startno) throws PyError {
		int ilineno = startno;
		// first line if :
		if (isExist(lines[ilineno], "\t+if\\s{1}.*\\:.*")) {
			// next \t\t hasBody
			while (isSpaceLine(lines[ilineno + 1])) {
				ilineno++;
			}
			if (ilineno + 1 < lines.length
					&& lines[ilineno + 1].indexOf("\t\t") >= 0) {
				return true;
			}
			throw new PyError("if statment has no body!", ilineno + 1);
		} else {
			throw new PyError("if xx==yy 's format is error!", ilineno);
		}

	}

	// not else and elif
	static boolean isIFStmtEnd(String[] lines, int startno) {
		if (startno >= lines.length) {
			return true;
		}
		if (isIFStmtELIF(lines, startno)) {
			return false;
		}
		return !isIFStmtELSE(lines, startno);
	}

	static boolean isIFStmtELIF(String[] lines, int startno) {
		if (startno >= lines.length) {
			return false;
		}
		return isExist(lines[startno], "\t+elif\\s{1}.*\\:.*");
	}

	static boolean isIFStmtELSE(String[] lines, int startno) {
		if (startno >= lines.length) {
			return false;
		}
		return isExist(lines[startno], "\t+else\\:");
	}

	public int parseStmtBranches(String[] lines, int startno) throws PyError {
		int ilineno = startno;
		if (isIFStmtEnd(lines, ilineno + 1)) {
			return startno;
		}
		while (isIFStmtELIF(lines, ilineno + 1)) {
			ELIFStatement elifStmt = new ELIFStatement(lines[ilineno + 1],
					ilineno + 1, ilineno + 1);
			this.branches.add(elifStmt);
			if (!elifStmt.checkStmt(lines, ilineno + 1)) {
				throw new PyError("Invalid elif stmt!", ilineno + 1);
			}
			ilineno = elifStmt.parseStmt(lines, ilineno + 1);
		}
		if (isIFStmtELSE(lines, ilineno + 1)) {
			ELSEStatement elseStmt = new ELSEStatement(lines[ilineno + 1],
					ilineno + 1, ilineno + 1);
			this.branches.add(elseStmt);
			if (!elseStmt.checkStmt(lines, ilineno + 1)) {
				throw new PyError("Invalid else stmt !", ilineno + 1);
			}
			ilineno = elseStmt.parseStmt(lines, ilineno + 1);
		}
		return ilineno;
	}

	// return lineno
	@Override
	public int parseStmt(String[] lines, int startno) throws PyError {
		int ilineno = startno;
		// first line if :
		if (isExist(lines[ilineno], "\t+if\\s{1}.*\\:.*")) {
			condition = lines[ilineno];
			while (isSpaceLine(lines[ilineno + 1])) {
				ilineno++;
			}
			// next \t\t hasBody
			while (ilineno + 1 < lines.length
					&& lines[ilineno + 1].indexOf("\t\t") >= 0) {
				bodies.add(lines[++ilineno]);
			}
			if (ilineno + 1 < lines.length) {
				ilineno = parseStmtBranches(lines, ilineno);
			}
		}
		return ilineno;
	}

	void printBranches() {
		for (IIFStatement stmt : branches) {
			stmt.printToString();
		}
	}

	@Override
	public void printToString() {

		super.printToString();
		printBranches();

	}

	@Override
	public String toString() {
		StringWriter sw = new StringWriter(128);
		sw.write(super.toString());
		for (IIFStatement stmt : branches) {
			sw.write(stmt.toString());
			// System.err.println(stmt.toString());
		}

		return sw.toString();

	}
}
