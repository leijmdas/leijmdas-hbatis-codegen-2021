package com.jtest.utility.testlog;


public interface IJTestLogger {

    String getSubsys();

    void setSubsys(String subsys);

    ch.qos.logback.classic.Logger getLogger();

    ch.qos.logback.classic.Logger getLoggerError();

    ch.qos.logback.classic.Logger getLoggerRun();

    ch.qos.logback.classic.Logger getLoggerDebug();

    ch.qos.logback.classic.Logger getLoggerJtest();

    void logDebug(Object msg);

    void logDebug(String title, Object msg);

    void logError(Object msg);

    void logError(Object title, Object msg);


    void logRun(Object msg);

    void logRun(String title, Object msg);


    //系统日志：springboot 非ytb软件
    void logService(Object title, Object msg);


    void logJtest(String title, Object msg);

    void logJtest(Object msg);

}