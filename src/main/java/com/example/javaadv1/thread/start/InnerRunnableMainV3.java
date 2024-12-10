package com.example.javaadv1.thread.start;

import static com.example.javaadv1.util.MyLogger.log;

public class InnerRunnableMainV3 {
    public static void main(String[] args) {
        log("main() start");
        // 익명 클래스 없이 변수에 바로 Runnable 전달
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log(Thread.currentThread().getName() + " run()");
            }
        });
        thread.start();
        log("main() end");
    }
}
