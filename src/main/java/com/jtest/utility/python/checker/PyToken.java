package com.jtest.utility.python.checker;

import com.jtest.utility.python.stmt.AssignStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PyToken {
	public PyToken(String value, int lineno) {
		this.value = value;
		this.lineno = lineno;
	}

	int lineno;
	int start;
	String value;

	public static String[] myTokens = {"test", "def", "=", "if", "elif",
			"else", "return" };
	public static String[] fnDefines = { "getAA", "getC", "getB", "getA" };

	public static String[] getTokens() {
		return myTokens;
	}

	public boolean isToken() {
		return isToken(value);
	}

	public static boolean isToken(String word) {
		for (String s : myTokens) {
			if (s.equals(word)) {
				return true;
			}
		}
		return false;

	}

	public boolean isDefVariable() {
		return isDefVariable(value);
	}

	public static boolean isDefVariable(String word) {
		for (String v : AssignStatement.getList()) {
			if (v.equals(word)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDefFunction() {
		return isDefFunction(value);
	}

	public static boolean isDefFunction(String word) {
		for (String f : fnDefines) {
			if (f.equals(word)) {
				return true;
			}
		}
		return false;
	}

	public boolean isConst() {
		return isDigit(value) || isQuote(value);
	}

	public static boolean isConst(String word) {
		return isDigit(word) || isQuote(word);
	}

	// digit
	static boolean isDigit(String word) {
		for (char c : word.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	static boolean isQuote(String word) {
		Pattern p = Pattern.compile("\'{1}\\w+\'{1}|\"{1}\\w+\"{1}");
		Matcher m = p.matcher(word);
		while (m.find()) {
			return true;
		}
		return false;
	}

	public static List<PyToken> parseWords(String line, int ilineno) {

		List<PyToken> v = new ArrayList<PyToken>();
		Pattern p = Pattern.compile("\'?\\w+\'?|\"?\\w+\"?");
		Matcher m = p.matcher(line);
		System.err.println("line=" + line);
		while (m.find()) {
			v.add(new PyToken(m.group(), ilineno));
			System.err.println("find=" + m.group());
		}

		return v;
	}

	public static void parseWordsUndef(PyContext ctx) throws PyError {
		System.out.println(ctx.getScripts());

		parseWordsUndef(ctx.getScripts(), 0);
	}

	public static void parseWordsUndef(String line, int lineno) throws PyError {
		List<PyToken> words = parseWords(line, lineno);

		for (PyToken token : words) {
			if (token.isConst()) {
				continue;
			} else if (token.isToken()) {
				continue;
			} else if (token.isDefVariable()) {
				continue;
			} else if (token.isDefFunction()) {
				continue;
			} else {
				throw new PyError(token + " is invalid", 0,
						PyError.ErrorType.SyntaxError);
			}

		}
	}
}
