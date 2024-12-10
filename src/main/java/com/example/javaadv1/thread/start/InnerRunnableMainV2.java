package com.example.javaadv1.thread.start;

import static com.example.javaadv1.util.MyLogger.log;

public class InnerRunnableMainV2 {
    public static void main(String[] args) {
        log("main() start");
        // 틀정 클래스에서만 사용할 수 있는 익명 클래스 사용
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log(Thread.currentThread().getName() + " run()");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        log("main() end");
    }
}
