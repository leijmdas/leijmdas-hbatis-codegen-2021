package com.jtest.testframe;

public class ITestError extends Error {
	String	msg	   = "";
	int	   retcode	= 0;
	
	public ITestError(int retcode, String msg) {
		this.msg = msg;
		this.retcode = retcode;
		
	}
	
	@Override
	public String toString() {
		return String.format("retcode=%d errmsg=%s ", retcode, msg);
	}
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4839885106838137264L;
	
}
