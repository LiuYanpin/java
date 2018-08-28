package com.cultivation.javaBasic;

public class VarLength {
    public String getArguments(Object... args) {
        return "getArguments";
    }

    public String getArguments(String arg1, Object... args) {
        return "multiArguments";
    }

    public String displayNumbers(int number1, int number2) {
        return "twoNumbers";
    }

    public String displayNumbers() {
        return "noArgument";
    }

    public String displayNumbers(int... args) {
        return "multiNumbers";
    }

    public String displayString(String ...parameter) {
        return "...parameter";
    }

    public String displayString(int i,String[] parameter) {
        return "parameter[]";
    }


}
