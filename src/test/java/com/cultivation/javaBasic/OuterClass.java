package com.cultivation.javaBasic;

public class OuterClass {
    static class StaticInnerClass {
        class InnerClass {

        }
    }
}

class InstanceClass {
    void createInnerClass() {
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
        staticInnerClass.new InnerClass();
    }
}

