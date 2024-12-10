package com.example.javaadv1.thread.start.test;

import static com.example.javaadv1.util.MyLogger.log;

public class StartTest3Main {
    public static void main(String[] args) {
        Thread counter = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                log(Thread.currentThread().getName() + " value: " + i);
                try {
                    Thread. sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "counter");
        counter.start();
    }
}
