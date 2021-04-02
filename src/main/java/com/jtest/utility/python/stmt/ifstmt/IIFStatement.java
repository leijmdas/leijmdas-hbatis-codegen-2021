package com.jtest.utility.python.stmt.ifstmt;


import com.jtest.utility.testlog.TestLog;
import com.jtest.utility.python.checker.PyError;
import com.jtest.utility.python.stmt.IStatement;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class IIFStatement extends IStatement {
	public IIFStatement(String line, int startline, int endline) {
		super(line, startline, endline);
		// TODO Auto-generated constructor stub
	}

	public List<IFStatement> bodyIfStmtList = new ArrayList<IFStatement>();

	public IIFStatement(StatementType ft) {
		_ft = ft;
	}

	static String IF = "if";
	static String ELIF = "elif";
	static String ELSE = "else";

	int itab;

	public String condition = null;
	List<String> bodies = new ArrayList<String>();

	public void clearBody() {
		bodies.clear();
	}

	@Override
	public String toString() {
		StringWriter sb = new StringWriter(128);
		int i = _startno;
		if (condition != null)
			sb.write(String.format("%d %s\n", i++, condition));
		for (String s : bodies) {
			sb.write(String.format("%d %s\n", i++, s));
		}

		return sb.toString();
	}

	public void printToString() {
		// System.out.print(toBody());
		TestLog.log(toString());
	}

	public static boolean isExist(String line, String reg) {

		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(line);
		return m.find();
	}

	public abstract boolean checkStmt(String[] lines, int ilineno)
			throws PyError;

	// return lineno
	@Override
	public abstract int parseStmt(String[] lines, int ilineno) throws PyError;

	public StatementType get_ft() {
		return _ft;
	}

	public void set_ft(StatementType _ft) {
		this._ft = _ft;
	}

}
