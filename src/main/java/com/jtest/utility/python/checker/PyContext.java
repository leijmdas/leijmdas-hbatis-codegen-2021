package com.jtest.utility.python.checker;

public class PyContext {
	String scripts = null;

	public PyContext(String scripts) {
		this.scripts = scripts;
		lines = scripts.split("\n");

	}

	String[] lines = new String[0];
	int lineno;
	int start;
	int end;

	public String getScripts() {
		return scripts;
	}

	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

	public int getLineno() {
		return lineno;
	}

	public String[] getLines() {
		return lines;
	}

	public void check() throws PyError {
		if (getScripts() == null || getScripts() == "") {
			throw new PyError("scripts is empty!", -1, null);
		}
	}
}
