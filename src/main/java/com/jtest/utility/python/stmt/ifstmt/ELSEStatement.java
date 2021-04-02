package com.jtest.utility.python.stmt.ifstmt;

import com.jtest.utility.python.stmt.IStatement;

public class ELSEStatement extends IIFStatement {
	public ELSEStatement(String line, int startline, int endline) {
		super(line, startline, endline);
		_ft = IStatement.StatementType.ELSE;
	}

	public ELSEStatement() {
		super(IStatement.StatementType.ELSE);
	}

	@Override
	public int parseStmt(String[] lines, int startno) {
		int ilineno = startno;
		// System.err.println("else parseStmt");
		// first line else :
		if (isExist(lines[ilineno], "\t+else\\:.*")) {
			// System.err.println("else parseStmt");
			condition = lines[ilineno];
			// next \t\t hasBody
			while (ilineno + 1 < lines.length
					&& lines[ilineno + 1].indexOf("\t\t") >= 0) {
				bodies.add(lines[++ilineno]);
			}
		}
		// System.out.printf("ELIFStatement ilineno=%d.\n", ilineno);
		return ilineno;
	}

	@Override
	public boolean checkStmt(String[] lines, int startno) {
		int ilineno = startno;
		return ilineno + 1 < lines.length
				&& lines[ilineno + 1].indexOf("\t\t") >= 0;

	}
}
