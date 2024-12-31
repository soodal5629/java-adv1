package com.example.javaadv1.thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 멀티 스레드 환경에서 안전하게 증가 연산을 수행할 수 있는 AtomicInteger 사용
 */
public class MyAtomicInteger implements IncrementInteger {
    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public synchronized void increment() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public synchronized int get() {
        return atomicInteger.get();
    }
}
