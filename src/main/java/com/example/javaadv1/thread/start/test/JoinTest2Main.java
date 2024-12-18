package com.example.javaadv1.thread.start.test;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class JoinTest2Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyTask(), "t1");
        Thread t2 = new Thread(new MyTask(), "t2");
        Thread t3 = new Thread(new MyTask(), "t3");
        t1.start(); // 3초
        t2.start(); // 3초
        t3.start(); // 3초
        // 모든 스레드 한번에 start
        t1.join(); // 대기
        t2.join(); // 대기
        t3.join(); // 대기
        System.out.println("모든 스레드 실행 완료");
    }
    static class MyTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        }
    }
}