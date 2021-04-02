package com.jtest.utility.Worker;

import com.alibaba.fastjson.JSONObject;
import com.jtest.utility.JAgent.IWorker;
import com.jtest.testframe.JTestRunner;

/**
 * Created by Administrator on 2017-7-1.
 */
public class TestcaseAgent implements IWorker {

    public StringBuilder execute(String p_json) {
        JSONObject j= JSONObject.parseObject(p_json);
        String suite=j.getString("suite");
        String test=j.getString("test");

        new JTestRunner(new String[]{suite,test});
        return new StringBuilder(j.toString());
    }
}
