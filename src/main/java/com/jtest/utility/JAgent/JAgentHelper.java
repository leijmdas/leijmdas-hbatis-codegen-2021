package com.jtest.utility.JAgent;

/**
 * Created by Administrator on 2017/7/1.
 */


import com.alibaba.fastjson.JSONObject;
import com.jtest.utility.Worker.TestcaseAgent;

import javax.management.*;
import javax.management.remote.*;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

public class JAgentHelper {
    static String user = "monitorRole";
    static String pwd = "password";
    static int port = 17783;
    static JMXConnectorServer cserver;
    static Class<?> cls;
    static JMXServiceURL address;//= new JMXServiceURL
    String jAgentPackage="com.jtest.utility.JAgent";
    
    //("service:jmx:rmi:///jndi/rmi://localhost:"+port+"/jmxrmi");
    public static void startAgent() throws Exception {

        startAgent("127.0.0.1", port);
    }

    public static void startAgent(String ip, int port) throws Exception {
        String url = "service:jmx:rmi:///jndi/rmi://" + ip + ":" + port + "/jmxrmi";

        LocateRegistry.createRegistry(port);// 必须要这句，参数为端口号
        ObjectName helloName = new ObjectName(
                "u7sejmock:type=com.jtest.utility.JAgent.JAgent,name=agent");
        JAgent hello = new JAgent();
        MBeanServer server = MBeanServerFactory.createMBeanServer();
        String[] credentials = new String[]{user, pwd};

        Map props = new HashMap();
        props.put("jmx.remote.credentials", credentials);
        JMXServiceURL address = new JMXServiceURL(url);

        cserver = JMXConnectorServerFactory
                .newJMXConnectorServer(address, props, server);
        cserver.start();
        server.registerMBean(hello, helloName);
        //cserver.stop();
        System.out.println(url);
    }

    public static void stopAgent() throws Exception {
        cserver.stop();
    }

    public static <T> T clientInst(Class<T> cls) throws Exception {
        return clientInst("127.0.0.1", port, cls);
    }

    public static <T> T clientInst(String ip, int port, Class<T> cls)
            throws Exception {
        JAgentHelper.cls = cls;
        String[] credentials = new String[]{user, pwd};
        Map props = new HashMap();
        props.put("jmx.remote.credentials", credentials);
        JMXServiceURL address = new JMXServiceURL(
                "service:jmx:rmi:///jndi/rmi://" + ip + ":" + port + "/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(address, props);

        MBeanServerConnection mbsc = connector.getMBeanServerConnection();

        connector.connect();

        ObjectName objectName = new ObjectName(
                "u7sejmock:type=com.jtest.utility.JAgent.JAgent,name=hello");
        if (!mbsc.isRegistered(objectName)) {
            mbsc.createMBean("com.jtest.utility.JAgent.JAgent", objectName, null, null);
        }

        System.out.println("\nMBean count = " + mbsc.getMBeanCount());
        final T hello = JMX.newMBeanProxy(mbsc, objectName, cls);
        return hello;
    }


    public static StringBuilder doTest(String ip, int port, String p_json) throws Exception {
        JSONObject j = JSONObject.parseObject(p_json);
        if(j.containsKey("type")){}else {
            j.put("type","com.jtest.utility.Worker.TestcaseAgent");
        }
        JAgentMBean agent = JAgentHelper.clientInst(ip, port, JAgentMBean
                .class);
        return agent.execute(j.toString());
    }

    public static void main(String[] args) throws Exception {
        startAgent();
        JAgentMBean h = clientInst(JAgentMBean.class);
        StringBuilder sb=h.execute("{}");
        System.out.println(sb);
        TestcaseAgent kl;

    }

}


