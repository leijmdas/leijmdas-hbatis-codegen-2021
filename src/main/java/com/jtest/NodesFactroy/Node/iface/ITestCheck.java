package com.jtest.NodesFactroy.Node.iface;

import com.jtest.testframe.ITestError;

import java.beans.beancontext.BeanContext;
import java.math.BigDecimal;

public interface ITestCheck {
	
	
    void checkEQ(Object  exp, Object act, String  prompt) throws ITestError;
	
	void checkRetcode(int exp, int act);
	void checkEQ(BigDecimal exp, BigDecimal act ) throws ITestError;

	void checkEQ(int exp, int act);
	
	void checkEQ(long exp, long ret);

	void checkEQ(boolean istrue);
	void checkEQ(boolean istrue,boolean exp);

	void checkEQ(String exp, String ret);

	void checkEQ(float exp, float ret);
}