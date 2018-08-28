package com.cultivation.javaBasic;

public class ExampleClass {
    private int dateOfBirth;
    private String name;
    private char gender;

    public ExampleClass(int dateOfBirth, String name, char gender) {
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.gender = gender;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
