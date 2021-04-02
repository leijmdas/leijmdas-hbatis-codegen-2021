package com.jtest.utility.testlog;


import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

//logger.setLevel();

public final class JTestLogger implements IJTestLogger {

    public String getSubsys() {
        return subsys;
    }

    public void setSubsys(String subsys) {
        this.subsys = subsys;
    }

    String subsys = "common";

    public JTestLogger(String subsys) {
        this.subsys = subsys;
         loggerRun = (Logger) LoggerFactory.getLogger(subsys);
    }

    public JTestLogger() {

    }

    public Logger getRootLogger() {
        return rootLogger;
    }

    public void setRootLogger(Logger rootLogger) {
        this.rootLogger = rootLogger;
    }

    Logger rootLogger =
            (Logger) LoggerFactory.getILoggerFactory().getLogger(
            "ROOT");
    //错误日志
    private Logger logger = (Logger) LoggerFactory.getLogger(JTestLogger.class);
    private Logger loggerError = (Logger) LoggerFactory.getLogger("ytberror");
    //运行日志
    private Logger loggerRun = (Logger) LoggerFactory.getLogger("ytbrun");

    //调试日志
    private Logger loggerDebug = (Logger) LoggerFactory.getLogger("ytbdebug");
    //jtest测试日志
    private  Logger loggerJtest = (Logger) LoggerFactory.getLogger("jtest");


    public  Logger getLogger() {
        return logger;
    }

    public  Logger getLoggerError() {
        return loggerError;
    }

    public  Logger getLoggerRun() {
        return loggerRun;
    }

    public  Logger getLoggerDebug() {
        return loggerDebug;
    }

    public  Logger getLoggerJtest() {
        return loggerJtest;
    }

    //和开源软件一起存日志
    public void logService(Object title, Object msg) {
        logger.debug(title.toString() + ":\r\n" + msg.toString());
    }


    public   void logRun(Object msg) {

        loggerRun.info(msg.toString());
    }

    public   void logRun(String title, Object msg) {
        loggerRun.info(title.toString() + ":\r\n" + msg.toString());
    }

    public   void logError(Object msg) {
        loggerError.error(msg.toString());
    }

    public   void logError(Object title, Object msg) {
        loggerError.error(title.toString() + ":\r\n" + msg.toString());
    }


    public   void logDebug(Object msg) {

            loggerDebug.debug(msg.toString());

    }

    public   void logDebug(String title, Object msg) {

            loggerDebug.debug("Begin " + title + ":\r\n" + msg.toString() + "\r\nEnd " + title);

    }

    public   void logJtest(Object msg) {
        System.out.println(msg.toString());
        loggerJtest.debug(msg.toString());

    }

    public   void logJtest(String title, Object msg) {
        System.out.println(title + ":\r\n" + msg.toString());
        loggerJtest.debug("Begin " + title + ":\r\n" + msg.toString() + "\r\nEnd " + title);

    }



}