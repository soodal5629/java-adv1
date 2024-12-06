package com.example.javaadv1.thread.start;

import static com.example.javaadv1.util.MyLogger.log;

public class ManyThreadMainV2 {
    public static void main(String[] args) {
        log("main() start");
        HelloRunnable runnable = new HelloRunnable();

        // 100개의 스레드에 같은 인스턴스를 넘겨주고 있음
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }

        log("main() end");
    }
}
