package com.jtest.utility.python.stmt.ifstmt;

public class ELIFStatement extends IIFStatement {
	public ELIFStatement() {
		super(StatementType.ELIF);
	}

	public ELIFStatement(String line, int startline, int endline) {
		super(line, startline, endline);
		_ft = StatementType.ELIF;
	}

	@Override
	public int parseStmt(String[] lines, int startno) {
		int ilineno = startno;
		// first line elif :
		if (isExist(lines[ilineno], "\t+elif\\s{1}.*\\:.*")) {
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
		// first line if :
		if (isExist(lines[ilineno], "\t+elif\\s{1}.*\\:.*")) {
			// next \t\t hasBody
			while (isSpaceLine(lines[ilineno + 1])) {
				ilineno++;
			}
			if (ilineno + 1 < lines.length
					&& lines[ilineno + 1].indexOf("\t\t") >= 0) {
				return true;
			}
		}
		return false;

	}
}
