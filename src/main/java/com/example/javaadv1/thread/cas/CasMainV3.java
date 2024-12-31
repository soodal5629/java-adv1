package com.example.javaadv1.thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.javaadv1.util.MyLogger.log;

public class CasMainV3 {
    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println("result = " + result);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            log("getValue: " + getValue);
            // 위에서 getValue 값을 읽고 그 사이에 값 변경이 안되었을 경우, getValue + 1을 해라
            // 만약 중간에 다른 스레드가 값을 변경했다면 result는 false가 되고 값이 똑같다면 true가 되어 while 문 탈출함
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result: " + result);
        } while(!result);

        return getValue+1;
    }
}
