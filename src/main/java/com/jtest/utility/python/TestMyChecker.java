package com.jtest.utility.python;


import com.jtest.annotation.JTest;
import com.jtest.utility.python.checker.PyChecker;
import com.jtest.utility.python.checker.PyError;
import com.jtest.utility.python.checker.PyParser;
import com.jtest.utility.python.checker.PyToken;
import com.jtest.utility.python.stmt.StmtMngr;
import com.jtest.utility.python.stmt.ifstmt.IFStatement;
import com.jtest.testframe.ITestFixture;

import java.io.StringWriter;
import java.util.List;

public class TestMyChecker extends ITestFixture {

	@Override
	public void setUp() {
		// pyParser = new PyParser();
	}

	@Override
	public void tearDown() {
	}

	//
	// public void rout() {
	// PrintStream ps = null;
	// PrintStream psout = System.out;
	// try {
	// ps = new PrintStream(new FileOutputStream("out.txt"));
	//
	// System.setOut(ps);
	// System.out.println("groovy");
	// System.out.println(new TestRegular());
	//
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } finally {
	// System.setOut(psout);
	// }
	//
	// }
	//
	// @JTest
	// public void testEval() {
	// String s = "System.out.println(\"aa\")";
	// Pattern p = Pattern.compile("\".+\"");
	// Matcher m = p.matcher(s);
	// if (m.find()) {
	// System.out.println(m.group().replaceAll("\"", ""));
	// }
	// }
	//
	@JTest
	public void testIFStmtIFEnd() {

		StringWriter sb = new StringWriter(120);
		sb.write("def main():\n");
		sb.write("\tif 1== 1 : \n");
		sb.write("\t\tret=1\n");
		sb.write("\t\tret=ret+1\n");
		sb.write("\treturn ret\n");
		String lines = sb.toString();
		PyParser.printLines(lines);
		List<PyToken> word = PyToken.parseWords(lines, 1);

		PyError pyerror = PyChecker.check(lines);
		assertTrue(pyerror.toString(), pyerror.isOk());
		List<IFStatement> list = StmtMngr.getIfStmtList();
		assertEquals(1, list.size());
		assertEquals(list.get(0).countElifBranches(), 0);

	}

	// @JTest
	// public void testIFStmtInvalidElif() {
	//
	// StringWriter sb = new StringWriter(120);
	// sb.write("def main():\n");
	// sb.write("\telif 1== 1 : \n");
	// sb.write("\t\tret=1\n");
	// sb.write("\t\tret=ret+1\n");
	// sb.write("\treturn ret\n");
	// String lines = sb.toString();
	// PyParser.printLines(lines);
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), !pyerror.isOk());
	// List<IFStatement> list = StmtMngr.getIfStmtList();
	// assertEquals(0, list.size());
	//
	// }
	//
	// @JTest
	// public void testIFStmtInvalidElse() {
	//
	// StringWriter sb = new StringWriter(120);
	// sb.write("def main():\n");
	// sb.write("\telse: \n");
	// sb.write("\t\tret=1\n");
	// sb.write("\t\tret=ret+1\n");
	// sb.write("\treturn ret\n");
	// String lines = sb.toString();
	// PyParser.printLines(lines);
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), !pyerror.isOk());
	// List<IFStatement> list = StmtMngr.getIfStmtList();
	// assertEquals(0, list.size());
	//
	// }
	// @JTest
	// public void testIFStmtELIFEnd() {
	//
	// StringWriter sb = new StringWriter(120);
	// sb.write("def main():\n");
	// sb.write("\tif 1== 1 : \n");
	// sb.write("\t\tret=1\n");
	// sb.write("\t\tret=ret+1\n");
	// sb.write("\telif 2==1: \n");
	// sb.write("\t\tret=1\n");
	// sb.write("\treturn ret\n");
	// String lines = sb.toString();
	// PyParser.printLines(lines);
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), pyerror.isOk());
	// List<IFStatement> list = StmtMngr.getIfStmtList();
	// this.assertEquals(1, list.size());
	// this.assertEquals(1, list.get(0).countElifBranches());
	//
	// }
	//
	// @JTest
	// public void testIFStmtElse() {
	//
	// StringWriter sb = new StringWriter(120);
	// sb.write("def main():\n");
	// sb.write("\tif 1== 1 : \n");
	// sb.write("\t\tret=1\n");
	// sb.write("\t\tret=ret+1\n");
	// sb.write("\telse: \n");
	// sb.write("\t\tret=1\n");
	// sb.write("\treturn ret\n");
	// String lines = sb.toString();
	// PyParser.printLines(lines);
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), pyerror.isOk());
	// List<IFStatement> list = StmtMngr.getIfStmtList();
	// this.assertEquals(1, list.size());
	// this.assertEquals(0, list.get(0).countElifBranches());
	// this.assertEquals(1, list.get(0).countElseBranches());
	//
	// }
	//
	// @JTest
	// public void testIFStmtELIF3() {
	//
	// StringWriter sw = new StringWriter(120);
	// sw.write("def main():\n");
	// sw.write("\tif 1== 1 : \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\telif 11==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 12==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 13==1: \n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\treturn ret\n");
	// String lines = sw.toString();
	// PyParser.printLines(lines);
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), pyerror.isOk());
	//
	// List<IFStatement> list = StmtMngr.getIfStmtList();
	// this.assertEquals(1, list.size());
	// this.assertEquals(list.get(0).countElifBranches(), 3);
	//
	// }
	//
	// @JTest
	// public void testIFStmtELIFElse() {
	//
	// StringWriter sw = new StringWriter(120);
	// sw.write("def main():\n");
	// sw.write("\tif 1== 1 : \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\telif 2==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telse: \n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\treturn ret\n");
	// String lines = sw.toString();
	// PyParser.printLines(lines);
	//
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), pyerror.isOk());
	// List<IFStatement> list = StmtMngr.getIfStmtList();
	// this.assertEquals(1, list.size());
	// this.assertEquals(list.get(0).countElifBranches(), 1);
	// this.assertEquals(list.get(0).countElseBranches(), 1);
	// }
	//
	// @JTest
	// public void testIFStmtELIF3Else() {
	//
	// StringWriter sw = new StringWriter(120);
	// sw.write("def main():\n");
	// sw.write("\tif: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\tif 1== 1 : \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\telif 12==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 22==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 32==1: \n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\telse: \n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\treturn ret\n");
	// String lines = sw.toString();
	// PyParser.printLines(lines);
	//
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), pyerror.isOk());
	// List<IFStatement> list = StmtMngr.getIfStmtList();
	// this.assertEquals(3, list.get(0).countElifBranches());
	// this.assertEquals(1, list.get(0).countElseBranches());
	//
	// }
	//
	// @JTest
	// public void testIFStmtELIFElse_elif() {
	//
	// StringWriter sw = new StringWriter(120);
	// sw.write("def main():\n");
	// sw.write("\tif: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\tif 1== 1 : \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\telif 12==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 22==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 32==1: \n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\telse: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 12==1: \n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\treturn ret\n");
	// String lines = sw.toString();
	// PyParser.printLines(lines);
	//
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), !pyerror.isOk());
	// // this.assertEquals(list.get(0).countElifBranches(), 3);
	//
	// }
	//
	// // 分析3条IF语句
	// @JTest
	// public void testIFStmt3() {
	// StringWriter sw = new StringWriter(120);
	// sw.write("def main():\n");
	// sw.write("\tif 1== 1 : \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\tif 2== 1 : \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\tif 3== 1 : \n");
	// sw.write("\t\tret='rggg'\n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\treturn ret\n");
	// String lines = sw.toString();
	// try {
	// sw.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// PyParser.printLines(lines);
	//
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), pyerror.isOk());
	// this.assertEquals(3, StmtMngr.getIfStmtList().size());
	// StmtMngr.printToString();
	//
	// }
	// @JTest
	// public void testIFStmtELIFElse_else() {
	//
	// StringWriter sw = new StringWriter(120);
	// sw.write("def main():\n");
	// sw.write("\tif d==d: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\tif 1== 1 : \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\telif 12==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 22==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 32==1: \n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\telse: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telse:\n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\treturn ret\n");
	// String lines = sw.toString();
	// PyParser.printLines(lines);
	//
	// PyError pyerror = pyParser.parse(lines);
	// this.assertTrue(pyerror.toString(), !pyerror.isOk());
	// // StmtMngr.printToString();
	// }
	//
	// @JTest
	// public void testIFStmtELIFElse_elsePrint() {
	//
	// StringWriter sw = new StringWriter(120);
	// sw.write("def main():\n");
	// sw.write("\taa= 1 : \n");
	// sw.write("\tif aa== 1 : \n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\tif 1== 1 : \n");
	// sw.write("\t\tret=ret+1\n");
	// sw.write("\t\tret=1\n");
	//
	// sw.write("\telif 12==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telif 22==1: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telse: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\tif d==d: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\t\tret=1\n");
	// sw.write("\telse: \n");
	// sw.write("\t\tret=1\n");
	// sw.write("\treturn ret\n");
	// String lines = sw.toString();
	// PyParser.printLines(lines);
	//
	// PyError pyerror = pyParser.parse(lines);
	// // this.assertTrue(pyerror.toString(), !pyerror.isOk());
	// StmtMngr.printToString();
	// }
}
