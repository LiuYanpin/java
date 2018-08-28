package com.cultivation.javaBasic;

public class MyClosableWithoutException implements AutoCloseable {
    private int id;
    private boolean isDone = false;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyClosableWithoutException(int id){
        this.id = id;
    }

    public boolean isClosed() {
        return true;
    }

    @Override
    public void close() {
        System.out.println("close"+isDone);
        this.isDone = true;
    }
}
