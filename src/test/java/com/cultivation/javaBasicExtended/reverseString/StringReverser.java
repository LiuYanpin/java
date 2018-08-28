package com.cultivation.javaBasicExtended.reverseString;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        if (input == null) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        if (input.trim().length() == 0) {
        //if (input.trim().isEmpty() == 0) {
            return new String[0];
        }
        // <--start
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = input.split(" ");
        String[] results = new String[words.length];
        for (int i = words.length-1; i >= 0; i--) {
            stringBuilder.append(words[i]).append(" ");
        }
        return stringBuilder.toString().split(" ");
        // --end-->
        //Collections.reverse(Arrays.asList());
    }
}
