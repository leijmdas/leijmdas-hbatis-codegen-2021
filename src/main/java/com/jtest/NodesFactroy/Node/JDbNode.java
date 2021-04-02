package com.jtest.NodesFactroy.Node;

import com.google.gson.Gson;
import com.jtest.testframe.ITestImpl;
import com.jtest.utility.testlog.TestLog;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JDbNode extends JDBInfo {

	public void setSql(StringBuilder sql) {
		this.sql = sql;
	}
	//transient String strsql;
	public StringBuilder getSql() {
		return sql;
	}

	transient StringBuilder sql = new StringBuilder(256);


	public JDbNode appendSql(Object s) {
		sql.append(s.toString());
		return this;
	}
	public JDbNode clearSql(){
		sql.delete(0,sql.length());
		return this;
	}

	transient ITestImpl impl =new ITestImpl();
	
	public JDBHelper getDb() {
		if(db==null){
			db=new JDBHelper(this);
		}
		return db;
	}
	
	transient  JDBHelper db;
	
	public JDbNode() {
		dbtype = DBType.DEFAULT;
		
	}
	
	public JDbNode closeDb() throws SQLException {
		getDb().close();
		return this;
	}
	
	public String toString() {
		return new Gson().toJson(this);
	}
	public JDbNode  sql(String strsql) {
		this.getSql().append(strsql);
		return this;
	}
	
	public int getRecordNumber() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		System.out.println(getSql().toString());
		return getDb().addSql(getSql().toString()).countSql();
		
		
	}
	
	public JDbNode  checkRecordNumber(int exp) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		getDb().checkEQ(exp,getRecordNumber(),"check RecordNumber");
		return this;
	}
	//"a=1|e2=2"
	public JDbNode  checkRecord(String exps) {
		
		Map<String,Object> exp =new LinkedHashMap<>();
		for(String f:exps.split("\\|"))
		{
			String[] sv=f.split("=");
			exp.put(sv[0],sv[1]); ;
		}
		
		return checkRecord(exp);
	}
	 JDbNode  checkRecord(Map<String,Object> exp) {
		getDb().checkRecord(exp);
		return this;
	}
	//id=1|name=张三
	public JDbNode  checkColumn(String exps) {
		
		Map<String,Object> exp =new LinkedHashMap<>();
		for(String f:exps.split("\\|"))
		{
			String[] sv=f.split("=");
			exp.put(sv[0],sv[1]);
		}
		
		return checkColumn(exp);
	}
	
	//id=1|name=张三
	 JDbNode checkColumn(Map<String, Object> exp) {
		for(Map.Entry<String,Object> e:exp.entrySet()){
			//System.out.println(e);
			TestLog.log(e);
		}
		getDb().checkColumn(exp);
		
		return this;
	}
	
	
	public <T> List<Map<String, Object>> select(T... params) throws  Exception {
		return getDb().addSql(getSql().toString()).select(params);
		
	}
	
	public JDbNode execSelect() throws  Exception {
		getDb().addSql(getSql().toString()).select();
		return this;
		
	}
	public JDbNode execSql() throws  Exception {
		getDb().addSql(this.sql.toString()).doSql();
		return this;

	}

	public JDbNode execSql(String sql) throws  Exception {
		getDb().addSql(sql).doSql();
		return this;
		
	}
	
	public JDbNode execSql(List<String> sqls) throws  Exception {
		for(String sql:sqls) {
			execSql(sql);
		}
		return this;
		
	}
}
