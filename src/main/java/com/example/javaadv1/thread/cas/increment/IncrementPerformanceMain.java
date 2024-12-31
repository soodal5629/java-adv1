package com.example.javaadv1.thread.cas.increment;

/**
 * 멀티 스레드 원자적 연산 아닌 기능들 성능 테스트
 */
public class IncrementPerformanceMain {
    public static final long COUNT = 100_000_000;

    public static void main(String[] args) {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) {
        long startMs = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }
        long endMs = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + ": ms = " + (endMs - startMs));
    }

}
