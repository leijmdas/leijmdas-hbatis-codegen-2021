package testcase

import com.jtest.testframe.JTestRunner
import com.jtest.utility.JAgent.JAgentHelper
import com.jtest.utility.JAgent.JAgentMBean
import com.jtest.NodesFactroy.Inject.Inject
import com.jtest.NodesFactroy.Node.HttpClientNode
import com.jtest.NodesFactroy.Node.JProxyNode

import com.jtest.NodesFactroy.NodeConfig.NodeConfig
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.jtest.annotation.JTestClass
import com.jtest.testframe.ITestImpl

/**
 * Created by ljm on 2012-04-02.
 */
@JTestClass.author("leijming")
@JTestClass.title("TestAgentNode")
class TestAgentNode extends ITestImpl {
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient

    @Inject(filename = "node.xml", value = "camweb")
    NodeConfig cfg

    public void suiteSetUp() {
        System.out.println("suiteSetup");
    }

    public void suiteTearDown() throws IOException {
        System.out.println("suiteTearDown");
    }

    public void setUp() {
        println httpclient
    }

    public void tearDown() throws IOException {}


    @JTestClass.title("test0001_A")
    @JTestClass.pre(" ")
    @JTestClass.step(" ")
    @JTestClass.exp(" ")
    void test0001_A() {

        println 'testcase.TestAgentNode'
        this.checkEQ(11,11)
    }

    @JTestClass.title("test0002_B")
    @JTestClass.pre(" ")
    @JTestClass.step(" ")
    @JTestClass.exp(" ")
    void test0002_B() {

        cfg.getProperties().each { println "2**" + it }

    }

    @JTestClass.title("test0003_C")
    @JTestClass.pre(" ")
    @JTestClass.step(" ")
    @JTestClass.exp(" ")
    void test0003_C() {
        JAgentHelper.startAgent("127.0.0.1", 12902);
        JAgentMBean agent = JAgentHelper.clientInst("127.0.0.1", 12902,
                JAgentMBean.class);
        JSONObject j = JSONObject.parseObject("{}")
        j.put('type', 'com.jtest.utility.Worker.TestcaseAgent')
        j.put('suite', 'testcase.TestJDbNode_dataNode')
        j.put('test', 'test0001_db_checkNum')

        StringBuilder sb = agent.execute(j.toString());
        println sb.toString()
        JAgentHelper.stopAgent()
    }

    @JTestClass.title("test0004_doTest")
    @JTestClass.pre("")
    @JTestClass.step("suite name/test name")
    @JTestClass.exp("ok")
    void test0004_doTest() {
//        JAgentHelper.startAgent("127.0.0.1", 12902);
//        try {
//
//            StringBuilder sb = JAgentHelper.doTest("127.0.0.1", 12902,
//                    "{'suite':'testcase.TestAgentNode','test':'test0001_A'}");
//            println sb.toString()
//        }
//        finally {
//            JAgentHelper.stopAgent()
//        }
    }

    @JTestClass.title("test0005_doTest_cfg")
    @JTestClass.pre("")
    @JTestClass.step("test cfg")
    @JTestClass.exp("ok")
    void test0005_doTest_cfg() {
//        def url = "http://127.0.0.1:8080/testweb.kunshan/rest/address/qryBy1/1,3";
//        String ret = httpclient.post(url, "{}", "application/json");
//        JSONObject.parseObject(ret, JSONArray.class).each {
//            println it
//        }
    }

    static void main(args) {
        //run(TestAgentNode.class, 3)
        new JTestRunner(TestAgentNode.class, 3);

    }
}
