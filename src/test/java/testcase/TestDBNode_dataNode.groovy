package testcase

import com.jtest.NodesFactroy.Inject.Inject
import com.jtest.NodesFactroy.Node.DBNode
import com.jtest.NodesFactroy.Node.JDbNode
import com.jtest.NodesFactroy.NodeConfig.DataNode
import com.jtest.annotation.JTest
import com.jtest.annotation.JTestClass
import com.jtest.testframe.ITestImpl

/**
 * Created by Administrator on 2012-04-02.
 */


//https://blog.csdn.net/cws1214/article/details/52922267
//消息队列
@JTestClass.author("leijm")
    class TestDBNode_dataNode extends ITestImpl {


        @Inject("sqlite")
        JDbNode dbnode
        @Inject(filename = "DataNode_Event.xml",value="EMP")
        DataNode dataNode;
        @Inject("sqlite")
        JDbNode jdbnode;
        public void suiteSetUp() {}

        public void suiteTearDown() throws IOException {}

        @Override
        void setUp() {
            println dataNode
            println dbnode
        }

        @Override
        void tearDown() {
        }

        @JTest
        void test0001_db_checkNum() {
            dbnode.clearSql().sql("select count(*) as cnt from t_addr where id=1")
                    .checkRecordNumber(1)
        }

        void test0002_db_checkRecord(){
            dbnode.clearSql().sql("select * from t_addr where id=1").select().each {
                println it
            }
            dbnode.checkRecord(id: 1, user_id: 1)   //id:172, user_id:111222, name:editname
        }

        void test0003_db_checkColumn() {
            dbnode.clearSql().sql("select * from t_addr where id<=2").select().each {
                println it
            }
            //dbnode.checkColumn(id: "1|1", user_id: "1|1")
        }

        void test0004_dataNode() {
            dataNode.all().each {
                println it
            }
            dataNode.allInsert().each {
                println it
            }
            println jdbnode.clearSql().sql("select * from t_user").getRecordNumber()
            jdbnode.checkRecordNumber(133)

        }
        //Logger logger=Logger.getLogger(Test_Inject.class.name);
        //PropertyConfigurator.configure(Test_Inject.class.getResource("/conf/log4j.properties").getFile());
        //logger.debug("demo")
        static void main(args) {
            run(TestDBNode_dataNode.class, 4);

        }
}
