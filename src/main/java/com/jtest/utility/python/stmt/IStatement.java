package com.jtest.utility.python.stmt;
 ;

 import com.jtest.utility.python.checker.PyError;
 import com.jtest.utility.python.checker.PyObject;

 import java.util.regex.Pattern;

public abstract class IStatement extends PyObject {
	protected enum StatementType {
		MAIN, RETURN, ASSIGN, IF, ELSE, ELIF, FOR, WHILE, MYFUNCTION, UNDEFINE
	}

	protected String[] _lines = {};
	protected int _startno;
	protected int _endno;

	public IStatement(StatementType ft) {
		_ft = ft;
	}

	public IStatement() {
		_ft = StatementType.UNDEFINE;
	}

	public IStatement(String line, int startline, int endline) {
		_lines = new String[] { line };
		_startno = startline;
		_endno = endline;
	}

	protected StatementType _ft = null;

	@Override
	public String toString() {
		return String.format("%d %s\n", _startno, _lines[0]);

	}

	// space indent
	public boolean isSpaceLine(String line) {
		Pattern p = Pattern.compile("\\s+");
		return Pattern.matches("\\s+", line);

	}

	public abstract int parseStmt(String[] lines, int ilineno) throws PyError;
}
