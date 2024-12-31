package com.example.javaadv1.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // compareAndSet(): 1. if 기대하는 value 값이 0이면 2. 1로 세팅하라 -> 원자적 연산이 아니다.
        // 근데 cpu가 이걸 원자적 연산으로 만들어줌
        boolean result1 = atomicInteger.compareAndSet(0, 1);
        System.out.println("result1 = " + result1 +  ", value = " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 100);
        System.out.println("result2 = " + result2 +  ", value = " + atomicInteger.get());
    }
}
