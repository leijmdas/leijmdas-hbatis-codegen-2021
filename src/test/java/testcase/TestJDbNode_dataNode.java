package testcase;


import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.NodesFactroy.NodeConfig.DataNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import com.jtest.utility.JTestUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JTestClass.author("leijm")
public class TestJDbNode_dataNode extends ITestImpl {
	@Inject("ytb_manager")
	JDbNode dbManager;
	@Inject("ytb_project")
	JDbNode dbProject;


	@Inject("ytb_user")
	JDbNode dbUser;

	@Inject("mysql")
	JDbNode dbNodeMysql;
	
	@Inject("sqlite")
	JDbNode dbnode;
	@Inject(filename = "DataNode_Event.xml", value = "EMP")
	DataNode dataNode;
	@Inject(filename = "node.xml", value = "httpclient")
	HttpClientNode httpclient;
	
	public void suiteSetUp() {
		System.err.println("suiteset");
		System.out.println(dbnode.toString());
		
		
	}
	
	public void suiteTearDown() throws IOException {
		System.out.println("close db :");
		System.out.println(dbnode.toString());
		try {
			dbnode.closeDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setUp() {
		
	}
	
	@Override
	public void tearDown() {
	}
	
	@JTest
	public void test0001_db_checkNum() throws Exception {
		
		dbnode.clearSql().sql("select * from t_user where id=49");
		dbnode.checkRecordNumber(1);
	}
	
	@JTest
	public void test0002_db_checkRecord() throws Exception {
		dbnode.clearSql().sql("select * from t_user where id=49");
		for (Map<String,Object> r: dbnode.select()){
			System.out.println(r);
		}
		dbnode.checkRecord("id=49|name=张三");
	}
	
	@JTest
	public void test0003_db_checkColumn() throws Exception {
		
		dbnode.clearSql().sql("select * from t_user where id<3");
		dbnode.execSelect().checkColumn("id=1,2|name=张三,张三");
	}
	
	
	@JTest
	public void test0004_dataNode() {
		for (String s : dataNode.all()) {
			System.out.println(s);
		}
		for (Object s : dataNode.allInsert()) {
			System.out.println(s);
		}
		
	}
	@JTest
	public void test0005_dataNode_insert() throws  Exception {
		//dbnode.clearSql().execSql("create table emp( id char(122),year int,day long
		// )");
		List<String> l=new ArrayList<>();
		for (Object s : dataNode.allInsert()) {
			System.out.println(s);
			dbnode.execSql(s.toString());
			l.add(s.toString());
		}
		dbnode.execSql(l);
		dbnode.clearSql().sql("select * from emp");
		for (Map<String,Object> r: dbnode.select()){
			System.out.println(r);
		}
	}
	@JTestClass.title("test0006_db_checkRecord")
	@JTestClass.pre("create table emp")
	@JTestClass.step("insert record")
	@JTestClass.exp("select record is OK")
	@JTest
	public void test0006_db_checkRecord() throws Exception {
		dbnode.clearSql().sql("select * from t_user where id=49");
		for (Map<String, Object> r : dbnode.select()) {
			System.out.println(r);
		}
		dbnode.checkColumn("id=49|name=张三");
	}
	
	
	@JTest
	public void test0007_db_selectByParam() throws Exception {
		dbnode.clearSql().sql("select * from t_user where id=? and name=?");
		List<Map<String, Object>> rs = dbnode.select(1, "张三");
		for (Map<String, Object> r : rs) {
			System.out.println(r);
		}
		dbnode.checkRecord("id=1");
	}
	
	@JTest
	public void test0008_db_selectMysql() throws Exception {
		dbProject.clearSql().appendSql("select status from project where project_id=278");
		dbProject.checkRecordNumber(1);
		dbProject.clearSql().appendSql("select * from project_talk where user_id2=232");
		dbProject.checkRecordNumber(0);
		dbUser.clearSql().appendSql(" select * from user_login ");
		dbUser.appendSql(" where user_id=").appendSql(1300);
		dbUser.appendSql(" limit 1");

		dbUser.select();
		System.out.println(JTestUtil.toJSONStringPretty(dbUser));
		System.out.println(JTestUtil.toJSONStringPretty(dbProject));
	}
	
	public static void main(String[] args) {
	run(TestJDbNode_dataNode.class, 8);
		//run(TestJDbNode_dataNode.class);

	}
}
		
		