package com.example.javaadv1.thread.start;

import static com.example.javaadv1.util.MyLogger.log;

public class InnerRunnableMainV4 {
    public static void main(String[] args) {
        log("main() start");
        // 람다 사용
        Thread thread = new Thread(() -> log(Thread.currentThread().getName() + " run()"));
        thread.start();
        log("main() end");
    }
}
