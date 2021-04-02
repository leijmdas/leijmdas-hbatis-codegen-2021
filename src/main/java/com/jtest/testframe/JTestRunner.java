package com.jtest.testframe;

import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class JTestRunner {



	public JTestRunner(Class<? extends ITestFixture>... ca) {
		for (Class<? extends ITestFixture> c : ca) {
			ITestSuite.RegTestSuite(c);
		}
		ITestSuite.run(new String[0]);
	}

	public JTestRunner(Class<?> c,String m) {
		ITestSuite.RegTestSuite(c);
		ITestSuite.showTestSuite();
		String[] args = {c.getName(), m};
		ITestSuite.run(args);
	}

	public class TMethod implements Comparable<TMethod> {
		public TMethod(Method m) {
			this.m = m;
		}
		Method m;

		public int compareTo(TMethod o) {
			return this.m.getName().compareTo(o.m.getName());
		}
	}

	public List<Method> getTestMethod(Class<?> c) {
		Method[] ms = c.getDeclaredMethods();
		List<TMethod> l = Lists.newArrayList();
		for (Method m : ms) {
			if (m.getName().startsWith("test")) {
				l.add(new TMethod(m));
			}
		}
		Collections.sort(l);
		List<Method> ll = Lists.newArrayList();
		for (TMethod t : l) {
			//System.out.println(t.m);
			ll.add(t.m);
		}

		return ll;

	}

	public JTestRunner(Class<?> c, int i) {
		ITestSuite.RegTestSuite(c);
		ITestSuite.showTestSuite();

		List<Method> lst = getTestMethod(c);
		int p=i <= lst.size() ?  i - 1:0;
		String[] args = {c.getName(), lst.get(p).getName()};
		ITestSuite.run(args);


	}

	public JTestRunner(Class<?> c) {

		ITestSuite.RegTestSuite(c);
		ITestSuite.showTestSuite();
		String[] args = {c.getName()};
		ITestSuite.run(args);
	}

	public JTestRunner(String arg) {

		ITestSuite.RegTestSuite(arg);
		ITestSuite.showTestSuite();
		String[] args = { arg };
		ITestSuite.run(args);
	}

	public JTestRunner(String args[]) {

		Date startdate = new Date();
		if (args.length == 0) {
			ITestSuite.RegTestSuite();
		}else{
			ITestSuite.RegTestSuite(args[0]);
		}
		ITestSuite.showTestSuite();
		ITestSuite.run(args);

		Date enddate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("\nstart date-end date:\t " + df.format(startdate)
				+ "\t" + df.format(enddate));
	}



}