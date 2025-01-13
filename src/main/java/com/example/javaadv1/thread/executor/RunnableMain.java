package com.example.javaadv1.thread.executor;

import java.util.Random;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();
        int res = task.value;
        log("result random value = " + res);
    }

    static class MyRunnable implements Runnable {
        // Runnable은 랜덤값을 바로 리턴할 수가 없어서 이렇게 값을 저장할 필드가 필요하다..
        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10); // 랜덤값
            log("create value = " + value);
            log("Runnable 완료");
        }
    }
}
