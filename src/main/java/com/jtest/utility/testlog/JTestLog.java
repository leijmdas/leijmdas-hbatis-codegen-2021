package com.jtest.utility.testlog;


import ch.qos.logback.classic.Logger;

public  class JTestLog {

    public static JTestLogger loggerImpl = new JTestLogger();

    public static Logger getRootLogger() {
        return rootLogger;
    }

    public static void setRootLogger(Logger rootLogger) {
        JTestLog.rootLogger = rootLogger;
    }

    public static Logger rootLogger = loggerImpl.getRootLogger();

    //和开源软件一起存日志
    public static void logService(Object title, Object msg) {
        loggerImpl.logService(title, msg);
    }



    public static void logRun(Object msg) {
        loggerImpl.logDebug(msg);
        loggerImpl.logRun(msg);
    }

    public static void logRun(String title, Object msg) {
        loggerImpl.logDebug(title, msg);
        loggerImpl.logRun(title, msg);
    }

    public static void logError(Object msg) {
        loggerImpl.logError(msg);
    }

    public static void logError(Object title, Object msg) {
        loggerImpl.logError(title, msg);
    }




    public static void logDebug(Object msg) {
        loggerImpl.logDebug(msg);

    }

    public static void logDebug(String title, Object msg) {
        loggerImpl.logDebug(title, msg);

    }

    public static void logJtest(Object msg) {
        loggerImpl.logJtest(msg==null?"logJtest null":msg);

    }

    public static void logJtest(String title, Object msg) {
        loggerImpl.logJtest("logJtest "+title, msg==null?"null":msg);

    }

}