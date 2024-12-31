package com.example.javaadv1.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static com.example.javaadv1.util.MyLogger.log;

public class CasMainV2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // 멀티스레드 환경에서 안전
//        int result = atomicInteger.incrementAndGet();
//        System.out.println("result = " + result);
//
//        int result2 = atomicInteger.incrementAndGet();
//        System.out.println("result2 = " + result2);

        AtomicInteger atomicInteger2 = new AtomicInteger(0);
        int result3 = incrementAndGet(atomicInteger2);
        System.out.println("result3 = " + result3);

        int result4 = incrementAndGet(atomicInteger2);
        System.out.println("result4 = " + result4);

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
