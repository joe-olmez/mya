package com.olmez.mya.temp;

public class SingletonExample {

    private SingletonExample instance;

    private SingletonExample() {
    }

    public SingletonExample getInstance() {
        if (instance == null) {
            synchronized (SingletonExample.class) {
                instance = new SingletonExample();
            }
        }
        return instance;
    }

}
