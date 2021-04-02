package com.jtest.utility.JAgent;

/**
 * Created by Administrator on 2017/7/1.
 */

/**
 * MBean
 * 包含在MBean中方法都将是可以被管理的。MBean起名是有规范的，就是原类名后加上MBean字样
 */
public interface JAgentMBean extends IWorker {
    StringBuilder execute(String p_json);

}
