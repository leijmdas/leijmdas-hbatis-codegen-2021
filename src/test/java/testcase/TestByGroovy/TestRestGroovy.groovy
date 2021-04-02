package testcase.TestByGroovy;

import com.alibaba.fastjson.JSONObject
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl

@JTestClass.author("leijm")
class TestRestGroovy extends ITestImpl {
    String url_base="http://localhost:8080/rest";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;


    //MsgRequest req = new MsgRequest();
    String data;

    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {
        data='''
{"token":"6ad9a0b9-3b61-4208-9de6-d8adfd99d28a","reqtime":1534043004468,"seqno":1534043004468,"cmdtype":"projectType","cmd":"getProjectTypeList","msgBody":{"x":1}}'''
        System.out.println(data);

     }

    @Override
    public void tearDown() {
    }


    @JTest
    @JTestClass.title("test0001_httprest1")
    @JTestClass.pre("")
    @JTestClass.step("post http://127.0.0.1:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0001_httprest1() {

//        String ret = httpclient.post(url_base+"/template",data , "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json=JSONObject.parseObject(ret);
//        checkEQ(0,json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("test0002_httprest2")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:8080/rest/system")
    @JTestClass.exp("ok")
    public void test0002_httprest2() {

    String ret = httpclient.post(url_base+"/system", data, "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json=JSONObject.parseObject(ret);
//        checkEQ(0,json.getInteger("retcode"));

    }

    @JTest
    @JTestClass.title("test0003_rest_template")
    @JTestClass.pre("")
    @JTestClass.step("post http://127.0.0.1:8080/WebMngr/rest/template")
    @JTestClass.exp("ok")
    public void test0003_rest_template() {

//        String ret = httpclient.post("http://localhost:8080/WebMngr/rest/template",data , "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json=JSONObject.parseObject(ret);
//        checkEQ(0,json.getInteger("retcode"));
//        System.out.println(data);
//        System.out.println(json.get("msgBody"));
//        System.out.println(json);
    }

    @JTest
    @JTestClass.title("test0004_rest_mngr")
    @JTestClass.pre("")
    @JTestClass.step("post http://127.0.0.1:8080/WebMngr/rest/manager")
    @JTestClass.exp("ok")
    public void test0004_rest_mngr() {
//
//        String ret = httpclient.post("http://localhost:8080/WebMngr/rest/manager",data , "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json=JSONObject.parseObject(ret);
//        checkEQ(0,json.getInteger("retcode"));
//        System.out.println(data);
//        System.out.println(json.get("msgBody"));
//        System.out.println(json);
    }
    public static void main(String[] args) {
        run(TestRestGroovy.class,4);

    }
}

