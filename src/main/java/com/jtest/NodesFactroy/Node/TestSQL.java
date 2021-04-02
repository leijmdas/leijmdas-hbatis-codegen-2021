package com.jtest.NodesFactroy.Node;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jtest.testframe.ITestError;

public class TestSQL {
	StringBuffer rawsql = new StringBuffer(1024);
	StringBuffer gensql = new StringBuffer(rawsql.capacity());
	
	class Params extends LinkedList<Object> implements Cloneable {
		public String name;
		
		@Override
		public Object clone() {
			Params o = null;
			o = (Params) super.clone();
			return o;
		}
		
	}
	
	Params params = new Params();
	
	public <T> void setParams(T t) {
		params.add(t);
	}
	
	public TestSQL clear() {
		rawsql.delete(0, rawsql.length());
		params.clear();
		return this;
	}
	
	public TestSQL append(String sql) {
		this.rawsql.append(sql);
		return this;
	}
	
	public TestSQL parseParams() {
		gensql.delete(0, gensql.length()).append(rawsql);
		
		Pattern p = Pattern.compile(":\\w+|\\?");
		Matcher m = p.matcher(gensql.toString());
		Params newps = (Params) params.clone();
		
		while (m.find()) {
			Object o = newps.poll();
			System.out.println(gensql);
			if (o == null) {
				System.err.println(gensql);
				throw new ITestError(-99005, "param no value!");
			}
			String value = o.toString();
			if (o instanceof String)
				value = "'" + value + "'";
			gensql.replace(m.start(), m.end(), value);
			m = p.matcher(gensql.toString());
		}
		
		// System.out.println(gensql);
		return this;
	}
	
	public String getSql() {
		parseParams();
		return gensql.toString();
	}
	
	public static void main(String[] args) {
		TestSQL sql = new TestSQL();
		sql.append("select * from a  where aa=:1").setParams(1222);
		System.out.println(sql.getSql());
	}
	
}
