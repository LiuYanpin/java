package com.cultivation.javaBasic;

public class MyClosableException implements AutoCloseable {
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

    public MyClosableException(int id) throws Exception {
        this.id = id;
        throw new Exception(String.valueOf(this.id));
    }

    public boolean isClosed() {
        return true;
    }

    @Override
    public void close() {
        System.out.println("close"+isDone);
        this.isDone = true;
        throw new IllegalArgumentException("IllegalArgumentException");
    }
}

