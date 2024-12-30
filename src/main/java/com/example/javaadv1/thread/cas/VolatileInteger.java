package com.example.javaadv1.thread.cas;

public class VolatileInteger implements IncrementInteger {
    volatile private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
