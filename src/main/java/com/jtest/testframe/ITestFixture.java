package com.jtest.testframe;


import com.jtest.NodesFactroy.Inject.NodesLoader;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.annotation.TestIgnore;
import com.jtest.utility.testlog.TestLog;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;

@JTestClass
public abstract class ITestFixture extends MyAssert {

	public ITestFixture() {
		new NodesLoader().load(this);
	}

	public void suiteSetUp() throws Exception {}

	public void suiteTearDown() throws Exception {}
	public void setUp() throws Exception {}

	public void tearDown() throws Exception {}


	public void run(TestSummary result) {

	}

	public int countTestcase() {
		return 0;
	}

	public void log(String inf) {
	 	TestLog.log(inf);
	}



	boolean isTest(Method m) {
		if (m.isAnnotationPresent(TestIgnore.class)) {
			return false;
		}

		if (m.getName().length() >= 4 && m.getName().startsWith("test")) {
			return true;
		}
		return m.isAnnotationPresent(JTest.class);
	}

	public void runTest(TestSummary testsummary) throws Exception {

		Set<DefineTestMethod> tests = getTestMethods();
		for (DefineTestMethod test : tests) {
			try {
				this.runTest(test.test, testsummary);
			} finally {

			}
		}

	}

	public void runTest(Method m, TestSummary testsummary)   {
		boolean iscatch = false;
		try {
			testsummary.incTcCount();
			testsummary.registerTcInf(this.getClass().getName() + "::" + m.getName(), m.getName());

//System.out.println("Enter\t" + this.getClass().getName() + "::" + m.getName());
			TestLog.log("Enter\t" + this.getClass().getName() + "::" + m.getName());

			this.setUp();
			m.invoke(this);
		} catch (ITestError itce) {

			testsummary.incTcFailed();
			testsummary.registerFailedTC(this.getClass().getName() + "::" + m.getName(), itce.getCause().toString());
			iscatch = true;
		} catch (Exception ex) {
			if (!iscatch)
				ex.printStackTrace();
			testsummary.incTcFailed();
			testsummary.registerFailedTC(this.getClass().getName() + "::" + m.getName(), ex.getCause().toString());
		} finally {
			try {
				this.tearDown();
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println("Exit\t" + this.getClass().getName() + "::" + m
			//		.getName() + "\n");
			TestLog.log("Exit\t" + this.getClass().getName() + "::" + m.getName() + "\n");

		}

	}

	public Set<DefineTestMethod> getTestMethods() {

		Set<DefineTestMethod> set = new TreeSet<DefineTestMethod>();

		for (Method m : getClass().getMethods()) {
			if (isTest(m)) {
				set.add(new DefineTestMethod(m));
			}
		}

		return set;

	}

	public class DefineTestMethod implements Comparable<DefineTestMethod> {

		@Override
		public int hashCode() {
			return test.hashCode();
		}

		public Method	test = null;

		public DefineTestMethod(Method m) {
			this.test = m;

		}

		public int compareTo(DefineTestMethod arg0) {
			return test.getName().compareTo(arg0.test.getName());
		}

	}
	public static void run(Class<?> c, String m) {
		new JTestRunner(c, m);

	}
	public static void run(Class<?> c, int... i) {
		for(int ii:i) {
			new JTestRunner(c, ii);
		}
		String code="1按代码规范\n" +
				"2不要有重复代码\n" +
				"3函数不能太长\n" +
				"4不能有if for else 好多{}嵌套\n" +
				"5尽量封装一些小函数";
		System.out.println(code);
	}

	public static void run(Class<?>  c) {
		new JTestRunner(c);

	}
}

