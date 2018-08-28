package com.cultivation.javaBasic.showYourIntelligence;

/*
 * 1.获取当前运行代码的类名，方法名，行号，主要是通过java.lang.StackTraceElement类
 *
 * 2.
 *   [1]获得调用者的方法名, 同new Throwable
 *         String _methodName = new Exception().getStackTrace()[1].getMethodName();
 *   [0]获得当前的方法名, 同new Throwable
 *         String _thisMethodName = new Exception().getStackTrace()[0].getMethodName();
 */
public class StackFrameHelper {
    public static String getCurrentMethodName() {
        Class currentClass = new Throwable().getStackTrace()[1].getClass();
        StringBuilder builder = new StringBuilder();
//        builder.append(currentClass.getClass())
        return new Throwable().getStackTrace()[1].getMethodName().toString();
    }
}
