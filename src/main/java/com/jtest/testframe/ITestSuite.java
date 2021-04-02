package com.jtest.testframe;

import com.jtest.annotation.TestIgnore;
import com.jtest.annotation.JTest;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.*;

public class ITestSuite extends Exception {
    static String homepath;
    protected static Map<String, ITestFixture> mapTest = new TreeMap<String, ITestFixture>();

    static boolean isStopWhenFailed = false;
    static TestSummary testSummary = new TestSummary();
    static PrintStream ps = null;



    public static Map<String, ITestFixture> getMapTest() {
        return mapTest;
    }

    public static void RegTestSuite(Object obj) {
        if (obj instanceof ITestFixture) {
            mapTest.put(obj.getClass().getName(), (ITestFixture) obj);
        }
    }

    public static void RegTestSuite(Class<?> testClass) {
        if (ITestFixture.class.isAssignableFrom(testClass)) {
            try {
                RegTestSuite(testClass.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void RegTestSuite(String className) {

        try {
            Class<?> cls = Class.forName(className);
            if(ITestFixture.class.isAssignableFrom(cls))
            {
                Class<ITestFixture> clsTest = (Class<ITestFixture>)cls;
                ITestFixture test = clsTest.newInstance();
                RegTestSuite(test);
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        } catch (NoClassDefFoundError ee) {
            //	ee.printStackTrace();
        }

    }

    // groovy show
    public static void showTestSuite() {

        for (Map.Entry<String, ITestFixture> entry : mapTest.entrySet()) {
            System.out.println("TestSuite is " + entry.getKey());
            ITestFixture itf = entry.getValue();
            System.out.println("TestSuite is " + itf.getClass().getSimpleName());
        }
    }

    static void reset() {
        testSummary.reset();

    }

    // groovy interface
    public static void run(String[] args) {
        try {
            reset();
            if (args.length == 0) {
                ITestSuite.runSuites();
            } else if (args.length == 1) {
                ITestSuite.runSuite(args[0]);
            } else if (args.length >= 2) {
                ITestSuite.runTest(args[0], args[1]);
            }
            testSummary.printTestResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
        }

    }

    // groovy all
    private static void runSuites() throws Exception {
        for (Map.Entry<String, ITestFixture> entry : mapTest.entrySet()) {
            entry.getValue().runTest(testSummary);
        }
    }

    // groovy one class method
    private static void runTest(String className, String methodName) throws Exception {
        runSuiteSetup(className);
        for (String key : mapTest.keySet()) {
            if (key.equals(className)) {
                runTest(mapTest.get(key), methodName);
                break;
            }
        }
        try {
            runSuiteTearDown(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // groovy one class
    private static void runSuite(String className) throws Exception {

        runSuiteSetup(className);
        for (String key : mapTest.keySet()) {
            if (key.equals(className)) {
                mapTest.get(key).runTest(testSummary);
                break;
            }
        }
    }

    private static void runSuiteSetup(String className) throws Exception {
        for (String key : mapTest.keySet()) {
            if (key.equals(className)) {
                mapTest.get(key).suiteSetUp();
                break;
            }
        }
    }

    private static void runSuiteTearDown(String className) throws Exception {
        for (String key : mapTest.keySet()) {
            if (key.equals(className)) {
                mapTest.get(key).suiteTearDown();
                break;
            }
        }
    }

    protected static void runTest(ITestFixture testFixture, String tmName) {

        Set<ITestFixture.DefineTestMethod> set = testFixture.getTestMethods();
        for (ITestFixture.DefineTestMethod m : set) {
            if (isTestMethod(m.test) && m.test.getName().equals(tmName)) {
                testFixture.runTest(m.test, testSummary);
            }
        }

    }

    static boolean isTestMethod(Method m) {
        if (m.isAnnotationPresent(TestIgnore.class)) {
            return false;
        }
        if (m.getName().length() >= 4 && m.getName().startsWith("test")) {
            return true;
        }
        return m.isAnnotationPresent(JTest.class);
    }

    // auto register TestSuite
    public static void RegTestSuite() {
        String classPath = System.getProperty("java.class.path");
        String paths[] = classPath.split(File.pathSeparator);
        List<String> alist = new ArrayList<String>();

        for (String path : paths) {
            if (path.endsWith(".jar")) {
                continue;
            }
            File dataDir = new File(path);
            homepath = path;
            list(dataDir, alist);
        }
        for (String testClassName : alist) {
            ITestSuite.RegTestSuite(testClassName);
        }

    }

    private static void list(File path, List<String> alist) {
        if (!path.exists()) {
            System.out.println("path no exists!");
        } else {
            if (path.isFile()) {
                System.out.println(path);
                String className = path.getAbsolutePath().replace(homepath + "\\", "").replace(".class", "");

                if (className.indexOf("$") < 0) {
                    alist.add(className.replaceAll("\\\\", "."));
                }

            } else {
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++) {
                    list(files[i], alist);
                }
            }
        }
    }

}
