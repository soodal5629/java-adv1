package com.example.javaadv1.thread.control.join;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class JoinMainV0 {
    public static void main(String[] args) {
        log("main start");
        Thread thread1 = new Thread(new Job());
        Thread thread2 = new Thread(new Job());
        thread1.start();
        thread2.start();
        log("main end");
    }

    static class Job implements Runnable {

        @Override
        public void run() {
            log("thread start");
            sleep(2000);
            log("thread end");
        }
    }
}
