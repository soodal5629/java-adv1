package com.example.javaadv1.thread.start;

import static com.example.javaadv1.util.MyLogger.log;

public class ManyThreadMainV1 {
    public static void main(String[] args) {
        log("main() start");
        HelloRunnable runnable = new HelloRunnable();
        // 3개의 스레드에 같은 인스턴스를 넘겨주고 있음
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        Thread thread3 = new Thread(runnable);
        thread3.start();
        log("main() end");
    }
}
