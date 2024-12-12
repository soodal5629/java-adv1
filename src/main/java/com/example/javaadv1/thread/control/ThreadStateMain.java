package com.example.javaadv1.thread.control;

import static com.example.javaadv1.util.MyLogger.log;

public class ThreadStateMain {
    // main 메소드 내 Thread.sleep 주는 이유는 myThread드 start 하고 너무 빨리 끝나서 상태 확인 못할 가능성이 있기 때문
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state 1 = " + thread.getState()); // NEW
        thread.start();
        Thread.sleep(1000);
        log("myThread.state 3 = " + thread.getState()); // TIMED-WAITING

        Thread.sleep(4000);
        log("myThread.state 5 = " + thread.getState()); // TERMINATED

    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                log("start!!");
                log("myThread.state 2 = " + Thread.currentThread().getState()); // RUNNABLE

                log("sleep() start");
                Thread.sleep(3000); // 자고 있는데 도중에 깨서 상태를 확인할 수 없음
                log("sleep() end");

                log("myThread.state4  = " + Thread.currentThread().getState()); // RUNNABLE
                log("end!!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
