package com.jtest.NodesFactroy.Node;

public class JDBInfo {

	
	DBType dbtype;
	
	String driver;
	String url;
	String user;

	String pwd;
	String name;
	String type;


	public DBType getDbtype() {
		return dbtype;
	}

	public void setDbtype(DBType dbtype) {
		this.dbtype = dbtype;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return dbtype.toString();
	}
	
	public JDBInfo() {
		dbtype = DBType.DEFAULT;
		//db=new JDBHelper(this);
	}
	
	public JDBInfo(String driver,String url, String usr, String pwd) {
		dbtype = DBType.DEFAULT;
		this.driver = driver;
		this.url = url;
		this.user = usr;
		this.pwd = pwd;
		
 	}
 	
	public enum DBType {
		DEFAULT,SQLITE, MYSQL, SQLSERVER;
	}
	
	public boolean isSqlserver() {
		return dbtype.equals(DBType.SQLSERVER);
	}
	
	public boolean isMysql() {
		return dbtype.equals(DBType.MYSQL);
	}
	
	public boolean isSqlite() {
		return dbtype.equals(DBType.SQLITE);
	}

}