package com.example.javaadv1.thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.javaadv1.util.MyLogger.log;

/**
 * AtomicBoolean 이용하여 락 획득/반납하는 것을 직접 구현
 */
public class SpinLock {
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("락 획득 시도");
        // AtomicBoolean 이용하여 CAS 연산 이용하여 락 획득
        while(!lock.compareAndSet(false, true)) {
            // 락 획득할 때까지 스핀 대기(바쁜 대기)한다.
            log("락 획득 실패 - 스핀 대기");
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock.set(false);
        log("락 반납 완료");
    }
}
