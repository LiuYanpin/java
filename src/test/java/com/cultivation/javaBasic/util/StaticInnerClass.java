package com.cultivation.javaBasic.util;

public class StaticInnerClass {

    public StaticInnerClass() {
        System.out.println("Static");
    }
    public Inner createInner() {
        return new Inner("Hello");
    }
    //如果一个类要被声明为static的，只有一种情况，就是静态内部类。
    //静态内部类使用场景一般是当外部类需要使用内部类，而内部类无需外部类资源，
    //并且内部类可以单独创建的时候会考虑采用静态内部类的设计，
    //不会引用外部类
    public static class Inner {
        private String name;

        public Inner(String name) {
            System.out.println("Inner");
            this.name = name;
        }

        public String getName() {
            return name;
        }

        class InnerClassClazz {
            public InnerClassClazz() {
                System.out.println("Inner");
            }
        }
    }
}
