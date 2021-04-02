package com.jtest.NodesFactroy.Node;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtest.NodesFactroy.Node.iface.ITestCheck;
import com.jtest.NodesFactroy.Node.iface.ITestDBHelper;
import com.jtest.testframe.ITestError;
import com.jtest.utility.testlog.TestLog;


public class JDBHelper implements ITestDBHelper, ITestCheck {
	Connection cn;
	List<Map<String, Object>> records = Lists.newArrayList();
	
	TestSQL sql = new TestSQL();
	
	JDBInfo dbinfo;
	
	public JDBHelper(JDBInfo dbinfo) {
		
		this.dbinfo = dbinfo;
	}
	public void checkEQ(BigDecimal exp, BigDecimal act) throws ITestError{
		if (exp.compareTo(act)!=0 ) {
			throw new ITestError(-99999, String.format("exp %s!=%s : act", exp, act));
		}
	}
	void log (String inf){
		System.out.println(inf);
		TestLog.log(inf);
	}
	public  void checkEQ(boolean exp,boolean act)
	{
		if (exp != act) {
			System.out.println("check "+act+"=="+act+"?false");
			throw new ITestError(-99999, String.format("exp %d!=%d : act", exp, act));
		}
		log("check "+act+"=="+act+"?true");
	}

	public Connection getConn(JDBInfo dbinfo) throws SQLException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {
		checkEQ(dbinfo.driver != null);
		Class.forName(dbinfo.driver);

		Connection cn = DriverManager.getConnection(dbinfo.url, dbinfo.user, dbinfo.pwd);
		checkEQ(cn != null);
		return cn;
	}
	
	@Override
	public void close() throws SQLException {
		if (cn != null) {
			cn.close();
			cn = null;
		}
	}


	@Override
	public void checkEQ(Object exp, Object act, String prompt) throws ITestError {

			if (!exp.toString().equals( act.toString())) {
				throw new ITestError(-99004, exp + " != " + act
						+ (prompt.length() > 0 ? " " + prompt  : ""));
			}
		log(exp + "=" + act);


	}

	@Override
	public void checkRetcode(int exp, int act) {
		checkEQ(exp, act );
	}
	
	@Override
	public void checkEQ(int exp, int act) {
		if (exp != act) {
			log("check "+exp+"=="+act+"?false");
			throw new ITestError(-99999, String.format("exp %d!=%d : act", exp, act));
		}
		log("check "+exp+"=="+act+"?true");
	}
	
	@Override
	public void checkEQ(long exp, long act) {
		if (exp != act) {
			log("check "+exp+"=="+act+"?false");
			throw new ITestError(-99999, String.format("exp %d!=%d : act", exp, act));
		}
		log("check "+exp+"=="+act+"?true");

	}
	
	@Override
	public void checkEQ(boolean act) {
		checkEQ(true  ,act);
		
	}
	
	@Override
	public void checkEQ(String exp, String act) {
		if (exp != act) {
			log("check "+exp+"=="+act+"?false");
			throw new ITestError(-99999, String.format("exp %d!=%d : act", exp, act));
		}
		log("check "+exp+"=="+act+"?true");

	}

	@Override
	public ITestDBHelper addSql(String asql) {
		sql.clear();
		sql.append(asql);
		return this;
	}

	@Override
	public void checkEQ(float exp, float act) {
		if (BigDecimal.valueOf(exp).compareTo(BigDecimal.valueOf(act)) != 0) {
			log("check " + exp + "==" + act + "?false");
			throw new ITestError(-99999, String.format("exp %s!=%s : act", exp + "", act + ""));
		}
		log("check " + exp + "==" + act + "?true");
	}

	@Override
	public <T> ITestDBHelper setParams(T... params) throws SQLException {

		for (T t : params) {
			sql.setParams(t);
		}
		return this;
	}
	
	@Override
	public <T> ITestDBHelper doSql(T... params) throws Exception {
		setParams(params);
		Statement st = getConn().createStatement();        // System.out.println(sql.getSql());
		st.executeUpdate(sql.getSql());
		st.close();
		return this;
	}
	
	// field name /type
	@Override
	public Map<String, String> selectDict(String tablename) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		Statement st = getConn().createStatement();
		log(tablename);
		ResultSet rs = st.executeQuery("select * from " + tablename.trim()
				+ " where 1=2");
		
		Map<String, String> dict = new LinkedHashMap<String, String>();
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		StringBuffer ret = new StringBuffer(1024);
		for (int col = 0; col < rsmd.getColumnCount(); col++) {
			dict.put(rsmd.getColumnName(col + 1).toLowerCase(),
					rsmd.getColumnTypeName(col + 1));
		}
		rs.close();
		st.close();
		return dict;
		
	}
	
	@Override
	public <T> String selectSql(T... params) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		setParams(params);
		Statement st = getConn().createStatement();
		
		ResultSet rs = st.executeQuery(sql.getSql());
		log(sql.getSql());
		ResultSetMetaData md = rs.getMetaData();
		
		StringBuffer ret = new StringBuffer(1024);
		for (int col = 0; col < md.getColumnCount(); col++) {
			ret.append(md.getColumnName(col + 1) + "|");
		}
		ret.append("\n");
		for (int col = 0; col < md.getColumnCount(); col++) {
			ret.append(md.getColumnTypeName(col + 1) + "|");
		}
		ret.append("\n");
		while (rs.next()) {
			for (int col = 0; col < md.getColumnCount(); col++) {
				ret.append(rs.getString(col + 1) + "|");
			}
			ret.append("\n");
			
		}
		rs.close();
		st.close();
		
		return ret.toString();
	}
	
	<T> void setPreStatement(PreparedStatement st, T... params) throws SQLException {
		int i = 1;
		for (Object o : params) {
			if (Float.class.isAssignableFrom(o.getClass()))
				st.setFloat(i++, (Float)o);
			
			else if (Double.class.isAssignableFrom(o.getClass()))
				st.setDouble(i++, (Double)o);
			else if (Short.class.isAssignableFrom(o.getClass()))
				st.setShort(i++, (short) o);
			else if (Integer.class.isAssignableFrom(o.getClass()))
				st.setInt(i++, (int) o);
			else if (Long.class.isAssignableFrom(o.getClass()))
				st.setLong(i++, (long) o);
			else if (String.class.isAssignableFrom(o.getClass()))
				st.setString(i++, o.toString());
			else if (Date.class.isAssignableFrom(o.getClass()))
				st.setDate(i++, (Date)o);
			else if (Timestamp.class.isAssignableFrom(o.getClass()))
				st.setTimestamp(i++, (Timestamp)o);
			else if (Time.class.isAssignableFrom(o.getClass()))
				st.setTime(i++, (Time)o);
			
		}
	}
	
	@Override
	public <T> List<Map<String, Object>> select(T... params)
			throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		setParams(params);
		
		List<Map<String, Object>> records = Lists.newArrayList();
		PreparedStatement st = getConn().prepareStatement(sql.rawsql.toString());
		
		setPreStatement(st,params);
		ResultSet rs = st.executeQuery();
		//System.out.println(sql.getSql());
		ResultSetMetaData md = rs.getMetaData();
		
		StringBuffer ret = new StringBuffer(1024);
		for (int col = 0; col < md.getColumnCount(); col++) {
			ret.append(md.getColumnName(col + 1) + "|");
		}
		ret.append("\n");
		for (int col = 0; col < md.getColumnCount(); col++) {
			ret.append(md.getColumnTypeName(col + 1) + "|");
		}
		ret.append("\n");
		while (rs.next()) {
			Map<String, Object> record = Maps.newLinkedHashMap();
			records.add(record);
			for (int col = 0; col < md.getColumnCount(); col++) {
				record.put(md.getColumnName(col + 1), rs.getObject(col + 1));
			}
			
		}
		rs.close();
		st.close();
		this.records = records;
		return records;
	}
	
	@Override
	public <T> ITestDBHelper checkSql(T... params) throws ITestError,
			SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		checkEQ(1, countSql(params));
		return this;
	}
	
	@Override
	public <T> int countSql(T... params) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		
		setParams(params);
		String s = sql.getSql();
		
		int cnt = 0;
		if (s.contains("count")) {
			Statement st = getConn().createStatement();
			ResultSet rs = st.executeQuery(s);
			cnt = rs.getInt(1);
			st.close();
			rs.close();
		} else {
			cnt = select().size();
		}
		
		System.out.println(s);
		return cnt;
	}
	
	public void checkRecord(Map<String, Object> exp) {
		if (records.size() > 0) {
			Map<String, Object> ret = records.get(0);
			for (String key : exp.keySet()) {
				checkEQ(exp.get(key).toString(), ret.get(key).toString(), "check first Record  " + key);
			}
		} else {
			throw new ITestError(-1, "no record!");
		}
		
	}
	
	public void checkColumn(Map<String, Object> exp) {
		if (records.size() > 0) {
			for (String key : exp.keySet()) {
				String v = exp.get(key).toString();
				String[] s = v.split(",");
				for (int i = 0; i < s.length; i++) {
					Map<String, Object> ret = this.records.get(i);
					checkEQ(s[i].toString(), ret.get(key).toString(), "check Column " + key);
				}
				
			}
		} else {
			throw new ITestError(-1, "no record!");
		}
		
	}
	
	@Override
	public Connection getConn() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (cn == null) {
			cn = getConn(dbinfo);
		}
		return cn;
	}
	
}
