package com.example.javaadv1.thread.start;

import static com.example.javaadv1.util.MyLogger.log;

public class InnerRunnableMainV1 {
    public static void main(String[] args) {
        log("main() start");
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        log("main() end");
    }

    // 여러 클래스에서 사용하지 않고 해당 클래스에서만 사용할 경우 static 중첩 클래스 사용
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log(Thread.currentThread().getName() + ": run()");
        }
    }
}
