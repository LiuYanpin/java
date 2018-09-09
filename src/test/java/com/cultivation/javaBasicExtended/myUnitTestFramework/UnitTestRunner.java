package com.cultivation.javaBasicExtended.myUnitTestFramework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.isPrivate;

class UnitTestRunner {

    UnitTestRunningResult run(Class<?> unitTestClass) {

        // TODO: please implement the following class to run all the tests.
        //Annotation, static
        // <--start
        if (unitTestClass.getName() == null) {
            try {
                throw new ClassNotFoundException("No Such Class.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        /*Class.forName(xxx.xx.xx) 返回的是一个类
        Class.forName(xxx.xx.xx);的作用是要求JVM查找并加载指定的类，也就是说JVM会执行该类的静态代码段
        动态加载和创建Class 对象，*/
        String unitTestClassName = unitTestClass.getName();
        Method[] methods = unitTestClass.getDeclaredMethods();
        List<TestResultItem> testResultItemList = new ArrayList<>();
        for (Method method : methods) {
            if (method.getAnnotation(MyTest.class) != null && !isPrivate(method.getModifiers())) {
                try {
                    method.invoke(unitTestClass.newInstance());
                    TestResultItem testResultItem = new TestResultItem(true, unitTestClassName, method.getName(), null);
                    testResultItemList.add(testResultItem);
                } catch (Exception e) {
                    String errorCauseMessage = null;
                    if (e.getCause() != null) {
                        errorCauseMessage = e.getCause().getMessage();
                    }else {
                        errorCauseMessage = "";
                    }
                    TestResultItem testResultItem = new TestResultItem(false, unitTestClassName, method.getName(), errorCauseMessage);
                    testResultItemList.add(testResultItem);
                }
            }
        }

        UnitTestRunningResult unitTestRunningResult = new UnitTestRunningResult(testResultItemList);
        return unitTestRunningResult;
        // --end-->
    }

    // TODO: You can add additional methods if you want
    // <--start
    // --end-->
}
