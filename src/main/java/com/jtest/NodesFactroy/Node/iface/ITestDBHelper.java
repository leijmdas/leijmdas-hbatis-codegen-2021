package com.jtest.NodesFactroy.Node.iface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jtest.NodesFactroy.Node.JDBInfo;
import com.jtest.testframe.ITestError;

public interface ITestDBHelper {
	public ITestDBHelper addSql(String sql);

	public <T> ITestDBHelper setParams(T... params) throws SQLException;

	public <T> ITestDBHelper doSql(T... params) throws Exception;

	public <T> String selectSql(T... params) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

	public <T> List<Map<String, Object>> select(T... params)
			throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

	public Map<String, String> selectDict(String tablename) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

	public <T> ITestDBHelper checkSql(T... params) throws ITestError,
			SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;

	public <T> int countSql(T... params) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

	public void close() throws SQLException;

	Connection getConn() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;

	Connection getConn(JDBInfo dbinfo) throws SQLException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException;
}
