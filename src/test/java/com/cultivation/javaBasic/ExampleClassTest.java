package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ExampleClassTest {
    @Test
    void should_get_gender() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ExampleClass exampleClass = new ExampleClass(1999, "Alice", '0');
        char gender = (char)exampleClass.getClass().getDeclaredMethod("getGender").invoke(exampleClass);
        assertEquals('0', gender);
    }

    @Test
    void should_get_name() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ExampleClass exampleClass = new ExampleClass(1992, "Kathy", '1');
        String name = (String) exampleClass.getClass().getDeclaredMethod("getName").invoke(exampleClass);
        assertEquals("Kathy", name);
    }

}