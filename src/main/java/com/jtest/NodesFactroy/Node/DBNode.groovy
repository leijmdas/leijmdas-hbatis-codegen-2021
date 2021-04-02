package com.jtest.NodesFactroy.Node

import com.google.gson.Gson
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import com.jtest.testframe.ITestImpl

/**
 * Created by Administrator on 2016-04-02.
 */
class DBNode {
    String name;
    String type;

    String url;
    String pwd;
    String user;
    String driver;

    transient String strsql;
    transient ITestImpl impl =new ITestImpl();
    transient Sql sql;
    private Sql getSql(){
        if(sql==null) {
         sql=   Sql.newInstance(url, user, pwd, driver)
        }
        sql
    }

    String toString() {
        return new Gson().toJson(this);
    }

    def getRecordNumber() {
        getSql().rows(strsql).get(0).get("cnt");
    }

    def checkRecordNumber(long exp) {
        def num = getRecordNumber()

        impl.checkEQ( exp.toString(),num.toString())
    }

    def checkRecord(Map<String,Object> exp) {
        def ret = select()
        def ret0=ret.get(0)
        exp.each {
            impl.checkEQ(it.getValue().toString(),ret0.get(it.getKey()).toString(),"check firstRecord "+it.getKey().toString() )
        }
    }

    def checkColumn(Map<String, Object> exp) {
        def ret = select()
        def ret0 = ret.get(0)
        exp.each {
            impl.checkEQ(it.getValue().toString(), ret0.get(it.getKey()).toString(), "check firstRecord " + it.getKey().toString())
        }
    }

    def sql(String strsql) {
        this.strsql = strsql
        return this
    }

    List<GroovyRowResult> select() {
        getSql().rows(strsql);
    }
}
