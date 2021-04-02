package testcase.study.groovy

import com.jtest.NodesFactroy.Inject.Inject
import com.jtest.NodesFactroy.Node.DBNode
import com.jtest.annotation.TestIgnore

import java.sql.Date
import java.sql.Timestamp

/**
 * Created by Administrator on 2016-03-25.
 */
/**
 * Created by Administrator on 2016-03-14.
 */
@Singleton
@TestIgnore
class TstSql_sinleton {//extends ITestFixture {
    @Inject("dbnode_pql")
    DBNode dbnode_pql
    //@Override
    void setUp() {

    }

    //@Override
    void tearDown() {

    }

    static void main(args) {
        def sql = TstSql_sinleton.instance.dbnode_pql.getSql();
        def a = 199
        def b = 199

        sql.execute("insert into newtable (col1, col2) values (?,?)", [a, b])
        sql.execute("update newtable set col2=? where col1=?", [999, 199])
        sql.executeUpdate("update newtable set col2 = ? where col1=?", [999, 199])

        sql.eachRow("select * from newtable", {
            println it.col1 + " -- ${it.col2} --"
        });
        sql.withBatch(3) { stmt ->
            stmt.addBatch "INSERT INTO newtable (col1, col2) VALUES ('1', '2')"
            stmt.addBatch "INSERT INTO newtable (col1, col2) VALUES ('1', '2')"
            stmt.addBatch "INSERT INTO newtable (col1, col2) VALUES ('1', '2')"
            stmt.addBatch "INSERT INTO newtable (col1, col2) VALUES ('1', '2')"
            stmt.addBatch "INSERT INTO newtable (col1, col2) VALUES ('1', '2')"
            stmt.addBatch "INSERT INTO newtable (col1, col2) VALUES ('1', '2')"
            stmt.addBatch "INSERT INTO newtable (col1, col2) VALUES ('1', '2')"
            stmt.addBatch "INSERT INTO newtable (col1, col2) VALUES ('1', '2')"
        }
        try {
            sql.execute '''
                          drop TABLE Author   ;'''
        }
        catch (e) {
            print e
        }
        try {
            sql.execute '''
                          CREATE TABLE Author  (
                            id          INTEGER  ,
                            firstname   VARCHAR(64),
                            lastname    VARCHAR(64),
                            birthday date,
                            createtime timestamp,
                            json  text
                          );'''
        }
        catch (e) {
            print e
        }
        sql.execute("delete from  Author");
        def d = new Date(System.currentTimeMillis())
        def t = new Timestamp(System.currentTimeMillis())
        for (i in 1..10) {
            sql.execute("insert into Author  values (?,?,?,? ,?,? )", [i, 'lei', 'jm', d, t, "2"])
        }
        def sqlString = "SELECT * FROM Author  FOR UPDATE";
        //        sql.getConnection().setAutoCommit(false)
        //        sql.eachRow(sqlString ) {
        //            row-> Clob clob = row.getClob("json");
        //            Writer outStream = clob.setCharacterStream(0L);
        //            char[] c = "ddd".toCharArray();
        //            outStream.write(c, 0, c.length)
        //            outStream.flush();
        //            outStream.close();
        //        }
        //        sql.commit()

        sql.eachRow("select * from Author ", {
            println it.id + " -- ${it.firstname} --" + it.lastname + "  " + it.birthday
        });
        sql.eachRow("select * from Author ", { println it })

        def rowCountAfter = sql.firstRow('SELECT COUNT(*) as num FROM Author').num

        println rowCountAfter;
    }


}

