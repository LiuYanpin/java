package com.cultivation.javaBasic.util;

public class InterfaceExtendsInterfaceWithDefaultMethodImpl implements InterfaceExtendsInterfaceWithDefaultMethod {
    @Override
    public String getTheTruthOfTheUniverse() {
        return "Game";
    }
}
class Whatever {
    String getMessage() {
        return null;
    }

    Object getMessage(int i) {
        return new Object();
    }
}

