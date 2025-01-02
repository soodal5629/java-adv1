package com.example.javaadv1.thread.cas.spinlock;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class SpinLockMain {
    public static void main(String[] args) {
        //SpinLockBad spinLock = new SpinLockBad();
        SpinLock spinLock = new SpinLock();
        Runnable task = () -> {
            spinLock.lock();
            try {
                // critical section
                log("비즈니스 로직 실행");
                sleep(1); // 오래 걸리는 로직에서 스핀 락 사용 x
            } finally {
                spinLock.unlock();
            }
        };
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
     }


}
