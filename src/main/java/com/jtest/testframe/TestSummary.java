package com.jtest.testframe;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

class TestResult {
}

public class TestSummary {
	
	int	                tcTotal	    = 0;
	int	                tcFailed	= 0;
	// Tc Information
	Map<String, String>	mapTcFailed	= new TreeMap<String, String>();
	Map<String, String>	mapTcTotals	= new TreeMap<String, String>();
	
	Date	            startdate;
	
	public TestSummary() {
		startdate = new Date();
	}
	
	String toDate() {
		Date enddate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat dfend = new SimpleDateFormat("hh:mm:ss");
		return "start date- end date:\t " + df.format(startdate) + "\t" + dfend.format(enddate) + "\n";
	}
	
	void printDate() {
		System.out.print(toDate());
	}
	
	public void reset() {
		tcTotal = 0;
		tcFailed = 0;
		mapTcFailed.clear();
		mapTcTotals.clear();
	}
	
	void incTcCount() {
		tcTotal++;
	}
	
	void incTcFailed() {
		tcFailed++;
	}
	
	public void registerTcInf(String tc, String inf) {
		
		mapTcTotals.put(tc, inf);
	}
	
	public void registerFailedTC(String tc, String inf) {
		mapTcFailed.put(tc, inf);
	}
	
	public void printTestResult() {
		System.out.println("\n****************Testresult************************************************************");
		printDate();
		System.out.printf("**************************************************************************************\n");
		System.out.printf("Total \ttestcase  =\t%d.\n", tcTotal);
		System.out.printf("Success\ttestcase  =\t%d.\t", tcTotal - tcFailed);
		System.err.printf("Failed\ttestcase  =\t%d.\n", tcFailed);
		// for (Entry<String, String> entry : mapTcTotals.entrySet()) {
		// }
		if (tcFailed > 0) {
			System.err.println("Failed testcase info:");
			for (Entry<String, String> entry : mapTcFailed.entrySet()) {
				System.err.println("\t" + entry.getKey() + "==>\t" + entry.getValue());
			}
		}
		System.out.printf("**************************************************************************************\n");
		
	}
	
	@Override
	public String toString() {
		StringWriter sw = new StringWriter(121);
		
		sw.write("\n****************Testresult********************************************\n");
		sw.write(toDate());
		sw.write("**********************************************************************\n");
		
		sw.write(String.format("Total \ttestcase Count =\t%d.\n", tcTotal));
		sw.write(String.format("Success\ttestcase Count =\t%d.\n", tcTotal - tcFailed));
		sw.write(String.format("Failed\ttestcase Count =\t%d.\n", tcFailed));
		sw.write("  Totals testcase info:\n");
		
		if (tcFailed > 0) {
			sw.write("Failed testcase info:\n");
			for (Entry<String, String> entry : mapTcFailed.entrySet()) {
				sw.write("\t" + entry.getKey() + "==>\t" + entry.getValue() + "\n");
			}
		}
		sw.write("***********************************************************************\n");
		for (String key : mapTcTotals.keySet()) {
			sw.write(key + "\n");
		}
		return sw.toString();
	}
}
