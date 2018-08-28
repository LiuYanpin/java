package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

@SuppressWarnings("unused")
public class PersonForEquals {
    private final String name;
    private final short yearOfBirth;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @SuppressWarnings("Contract")
    @Override
    public boolean equals(Object obj) {
        // TODO: please modify the following code to pass the test
        // <--start
        if (this == obj) {
            return true;
        }
        if (this == null){
            return false;
        }

        if (getClass() != obj.getClass()){
            return false;
        }

        if (obj instanceof PersonForEquals){
            PersonForEquals anotherObj = (PersonForEquals)obj;
            if (anotherObj.getName() == this.getName() && anotherObj.getYearOfBirth() == this.getYearOfBirth()){
                return true;
            }
        }
        return false;
        // --end-->
    }

    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        return Objects.hash(this.getName(),this.getYearOfBirth());
        //throw new NotImplementedException();
        // --end-->
        /*public static int hashCode(Object a[]) {
            if (a == null)
                return 0;

            int result = 1;

            for (Object element : a)
                result = 31 * result + (element == null ? 0 : element.hashCode());

            return result;
        }*/
    }
}
