package com.jtest.testframe;


import com.jtest.NodesFactroy.Node.iface.ITestCheck;

import java.math.BigDecimal;

public class ITestImpl extends ITestFixture implements ITestCheck {

	public void checkEQ(BigDecimal exp, BigDecimal act) throws ITestError{
		if (exp.compareTo(act)!=0 ) {
			throw new ITestError(-99999, String.format("exp %s!=%s : act", exp, act));
		}
	}
	public void checkEQ(Object exp, Object act, String  prompt)
			throws ITestError {
		if (!exp.toString().equals( act.toString()) ) {
			throw new ITestError(-99004, exp + " != " + act
					+ (prompt.length() > 0 ? " " + prompt  : ""));
		}
		System.out.println(exp + "=" + act);
	}

	public void checkRetcode(int exp, int act) {
		if (exp != act) {
			throw new ITestError(-99999, String.format("exp %d!=%d : act", exp, act));
		}
	}

	@Override
	public void checkEQ(int exp, int act) {

		if (exp != act) {
			System.out.println("check "+exp+"=="+act+"?false");
			throw new ITestError(-99999, String.format("exp %d!=%d : act", exp, act));
		}
		System.out.println("check "+exp+"=="+act+"?true");

	}


	@Override
	public void checkEQ(float exp, float act) {
		if (BigDecimal.valueOf(exp).compareTo(BigDecimal.valueOf(act)) != 0) {
			System.out.println("check " + exp + "==" + act + "?false");
			throw new ITestError(-99999, String.format("exp %s!=%s : act", exp + "", act + ""));
		}
		System.out.println("check " + exp + "==" + act + "?true");
	}

	@Override
	public void checkEQ(long exp, long ret) {
		if (exp != ret) {
			throw new ITestError(-99999, String.format("exp %lld!=%lld : act", exp, ret));
		}
	}

	@Override
	public void checkEQ(boolean istrue) {
		if (!istrue) {
			throw new ITestError(-99999, "false:");
		}
	}

	@Override
	public void checkEQ(boolean exp, boolean act) {
		System.out.println("check "+exp+"=="+act);
		if (exp == act) {
			throw new ITestError(-99999, String.format("exp %s != %s : act", exp, act));
		}
		System.out.println("result "+exp+"=="+act+ " OK ");
	}



		@Override
	public void checkEQ(String exp, String act) {
		System.out.println("check "+exp+"=="+act);
		if (!exp.equals( act )) {
			throw new ITestError(-99999, String.format("exp %s != %s : act", exp, act));
		}
		System.out.println("result "+exp+"=="+act+ " OK ");

	}

//	public static void main(String args[]) throws Exception {
//
//		new ITestImpl().runTest(new TestSummary());
//
//	}
}
